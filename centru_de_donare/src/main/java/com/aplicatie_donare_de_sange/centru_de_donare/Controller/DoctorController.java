package com.aplicatie_donare_de_sange.centru_de_donare.Controller;

import com.aplicatie_donare_de_sange.centru_de_donare.Dto.DoctorDto;
import com.aplicatie_donare_de_sange.centru_de_donare.Dto.DonatorDto;
import com.aplicatie_donare_de_sange.centru_de_donare.Model.Doctor;
import com.aplicatie_donare_de_sange.centru_de_donare.Model.Donator;
import com.aplicatie_donare_de_sange.centru_de_donare.Model.Locatie;
import com.aplicatie_donare_de_sange.centru_de_donare.Model.Programare;
import com.aplicatie_donare_de_sange.centru_de_donare.service.DoctorService;
import com.aplicatie_donare_de_sange.centru_de_donare.service.LocatieService;
import com.aplicatie_donare_de_sange.centru_de_donare.service.ProgramariService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DoctorController {

    private DoctorService doctorService;
    private LocatieService locatieService;
    private ProgramariService programariService;
    private UserDetailsService userDetailsService;

    public DoctorController(DoctorService doctorService, LocatieService locatieService, ProgramariService programariService, UserDetailsService userDetailsService) {
        this.doctorService = doctorService;
        this.userDetailsService = userDetailsService;
        this.locatieService = locatieService;
        this.programariService = programariService;

    }

    @GetMapping("/doctor")
    public String doctorPage(Model model) {

        List<Locatie> locatie = new ArrayList<>();

        List<Locatie> allLocatii = locatieService.getAllLocatii();


        //List<Programare> allProgramari =  programariService.getProgramari()

        model.addAttribute("locatie", locatie);
        model.addAttribute("allLocatii", allLocatii);


        return "doctor";
    }

    @GetMapping("/doctor/info")
    public String doctorView(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        Doctor doctor = doctorService.findByUsername(currentPrincipalName);

        System.out.println(currentPrincipalName);
        System.out.println(doctor);

        Locatie locatie = doctor.getLocatie();

        System.out.println(locatie);

        List<Programare> programari = programariService.getAllLocationProgram(locatie);

        model.addAttribute("doctorCurent", doctor);

        model.addAttribute("locatieCurenta", locatie);

        model.addAttribute("allProgramari", programari);


        return "doctorview";
    }

    @ModelAttribute("doctor")
    public DoctorDto userRegistrationDto() {
        return new DoctorDto();
    }

    @PostMapping("/doctor")
    public String inserareDoctor(@ModelAttribute("doctor") DoctorDto registrationDto) {

        registrationDto.setRole("ROLE_DOCTOR");
        registrationDto.setActivate(true);

        System.out.println(registrationDto);

        doctorService.save(registrationDto);
        return "redirect:/doctor?success";
    }

    @PostMapping("/doctor/delete/{id}")
    public String deleteDoctor(@PathVariable Long id) {


        Doctor doctor = doctorService.getOne(id);

        System.out.println(doctor);

        doctorService.deleteDoctor(doctor.getId());


        return "redirect:/admin/?succes";

    }


    //&+*************************

    @GetMapping("doctor/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Doctor doctorAux = doctorService.getOne(id);


        List<Locatie> locatie = new ArrayList<>();
        List<Locatie> allLocatii = locatieService.getAllLocatii();

        model.addAttribute("locatie", locatie);
        model.addAttribute("allLocatii", allLocatii);
        model.addAttribute("doctorAux", doctorAux);
        model.addAttribute("oldPass", doctorAux.getPassword());


        return "doctorUpdate";
    }

    @PostMapping("doctor/update/{id}")
    public String updateUser(@PathVariable("id") long id, @ModelAttribute("doctorAux") Doctor doctor, @ModelAttribute("oldPass") String oldPass, Model model) {

        if (doctor.getPassword().equals(""))
            doctor.setPassword(oldPass);


        doctorService.save(doctor);

        return "redirect:/admin";

    }

    @PostMapping("/doctor/modificaStarea/{id}")
    public String modificaStareaProgramarii(@PathVariable("id") long id){

        System.out.println("Am ajuns aici! DOCTOR: donare cu succes");

        programariService.modificaStarea(id);

        return "redirect:/doctor/info";
    }

    @PostMapping("/doctor/modificaStareaEsec/{id}")
    public String modificaStareaProgramariiEsec(@PathVariable("id") long id){

        System.out.println("Am ajuns aici! DOCTOR: esec (absent donator)");

        programariService.modificaStareaEsec(id);

        return "redirect:/doctor/info";
    }

}
