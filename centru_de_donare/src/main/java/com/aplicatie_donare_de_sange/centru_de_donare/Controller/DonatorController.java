package com.aplicatie_donare_de_sange.centru_de_donare.Controller;

import com.aplicatie_donare_de_sange.centru_de_donare.Model.DataProgramare;
import com.aplicatie_donare_de_sange.centru_de_donare.Model.Donator;
import com.aplicatie_donare_de_sange.centru_de_donare.Model.Locatie;
import com.aplicatie_donare_de_sange.centru_de_donare.Model.Programare;
import com.aplicatie_donare_de_sange.centru_de_donare.service.DonatorService;
import com.aplicatie_donare_de_sange.centru_de_donare.service.LocatieService;
import com.aplicatie_donare_de_sange.centru_de_donare.service.ProgramariService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class DonatorController {

    private DonatorService donatorService;
    private LocatieService locatieService;
    private ProgramariService programariService;

    public DonatorController(DonatorService donatorService , LocatieService locatieService , ProgramariService programariService){
        this.donatorService = donatorService;
        this.locatieService = locatieService;
        this.programariService = programariService;
    }

    @GetMapping("/donator")
    public String homeDonator(Model model) throws ParseException {
        model.addAttribute("date",new DataProgramare());
        List<Locatie> locatii = locatieService.getAllLocatii();



        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        System.out.println(currentPrincipalName);

        Donator curent = donatorService.findByUsername(currentPrincipalName);

        List<Programare> programariDonator = programariService.programariDonator(curent);

        List<Programare> programariFinalizate = programariService.getProgramariFinalizare(curent);

        List<Programare> programariInitiate = programariService.getProgramariInitiate(curent);

       // programariFinalizate.stream().forEach(t-> System.out.println(t));

        //programariDonator.stream().forEach(t-> System.out.println(t));

        model.addAttribute("allProgramari",programariDonator);

        List<String> allDates = formator(programariDonator);

        model.addAttribute("allDates",allDates);

        model.addAttribute("allLocatii" , locatii);

        model.addAttribute("donatorCurent",curent);

        model.addAttribute("allProgramariFinalizate" , programariFinalizate);

        model.addAttribute("allProgramariInitiate",programariInitiate);



        return "donator";
    }

    @PostMapping("/donator/delete/{id}")
    public String stergeDonatorulCurent(@PathVariable Long id){

       Donator donator = donatorService.getOne(id);

        System.out.println(donator);

        List<Programare> allProgramari = programariService.getAll(donator);

        //System.out.println(allProgramari);

        programariService.deleteManyProgramari(allProgramari);

        donatorService.deleteDonator(donator.getId());

       return "redirect:/login";
    }

    @PostMapping("/donator/creareProgramare/{id}")
    public String creareProgramare(@PathVariable Long id){

        Locatie locatie = locatieService.getOne(id);

        System.out.println(locatie);

        return "redirect:/donator";
    }

    public List<String> formator(List<Programare> programari) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
        DateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy");

        List<String> strings = new ArrayList<>();

        for(Programare p:programari) {
            Date date = p.getDate();
            String dateFinal = formatter1.format(formatter.parse(date.toString()));
            strings.add(dateFinal);
        }
        return strings;
    }

    @PostMapping("/submit/{id}")
    public String submitForm(Model model, @ModelAttribute("date") DataProgramare date , @PathVariable Long id) throws ParseException {

        Date dateAux = date.getDate();

       /* DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
        DateFormat formatter1 = new SimpleDateFormat("dd.MM.yyyy");

        String dateFinal = formatter1.format(formatter.parse(dateAux.toString()));*/

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        System.out.println(currentPrincipalName);

        Donator curent = donatorService.findByUsername(currentPrincipalName);

        System.out.println(curent);


        System.out.println(id);

        Locatie locatie = locatieService.getOne(id);

        System.out.println(locatie);


        System.out.println(date);

        Programare pSaved = programariService.save(curent,locatie,dateAux);

        System.out.println(pSaved);

        return "redirect:/donator";
    }


    @GetMapping("donator/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Donator donator = donatorService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("donator", donator);

        return "donatorUpdate";
    }

    @PostMapping("donator/update/{id}")
    public String updateUser(@PathVariable("id") long id,Donator donator, Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Donator curent = donatorService.findByUsername(currentPrincipalName);

        donator.setActive(true);
        donator.setRole("ROLE_DONATOR");

        System.out.println(curent.getUsername());
        System.out.println(donator.getUsername());

        if(donator.getPassword().equals(""))
            donator.setPassword(curent.getPassword());

        donatorService.update(donator);

        if(!curent.getUsername().equals(donator.getUsername()))
            return "redirect:/login";
        else
            return "redirect:/donator";

    }

    @PostMapping("/donator/anuleaza/{id}")
    public String anuleazaProgramarea(@PathVariable("id") long id){

         System.out.println("Donator : Am ajuns aici!");

        programariService.anuleazaProgramarea(id);

        return "redirect:/donator";
    }


}
