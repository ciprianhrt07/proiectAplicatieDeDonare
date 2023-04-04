package com.aplicatie_donare_de_sange.centru_de_donare.Controller;

import com.aplicatie_donare_de_sange.centru_de_donare.Model.Doctor;
import com.aplicatie_donare_de_sange.centru_de_donare.service.DoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

/* las aici un comment pentru toate controllerele

    mi se pare că ai combinat cumva ideea de
    MVC (un controller poate gestiona call-uri din mai multe view-uri) cu
    MVVM (câte un "controller" aka "view-model" pentru fiecare view)

    de ce spun asta? Pentru doctor ai 3 view-uri și un controller (am crezut MVC)
    dar ai și un controller per view când vine vorba de pagina de admin de exemplu (care de fapt face call tot la logica pentru doctor) (MVVM)

    ideea de la MVVM e că dacă am nevoie de modificări în pagină,
    și am nevoie să îi modific controllerul (Adică acel obiect numit view-model)
    voi modifica doar view-ul și view-model-ul (două clase interconectate între ele, care nu au trebă cu altele)

    dacă structuram ca MVC și aveam nevoie de modificări într-un view
     care ajungea să necesite modificări în controller
    se poate ca modificarea să reverbereze în celelalte view-uri, pe care controller-ul le controlează

    dar bucuria la MVC e că am o zonă centrală (un singur controlelr)
    pentru toate view-urile care țin de un anumit domeniu (de ex: doctor)
    pot refolosi ușor cod, văd unde/ce logică e folosită și
    am o singură clasă care e cuplată cu toate serviciile de care mai are nevoie entitatea mea
    (de ex nu am alte clase care să fie cuplate la doctorservice, programariservice, etc)

    MVVM a venit ca o soluție la MVC când controller-ele au ajuns să fie mult prea mari, să aibe prea multă logică
    dacă te gânești că poți ajunge în cazul acea, MVVM se potrivește

    dacă există șansele să ajungi la un controller masiv (the massive view controller issue)
    are rost să faci MVVM
    dar altfel, mi se pare că adaugi confuzie în cod.

    aici ar merge un MVC

    la mvc: pentru că o pagina care se numește /admin dar în ea sunt doctori
    poate aș face doar o metodă în DoctorContrller pentru a primi toți doctorii, și aș pune doctorii într-un model
    și asta ne duce la următoarea problemă: unde punem getMapping pentru /admin
    păi ne dăm seama că de fapt pagina nu are nimic de a face cu adminii, doar că "doar un admin o poate accesa"
    și de fapt pagina aia e /doctors ;)

*/


@Controller
public class AdminController {

    private  DoctorService doctorService;

    public AdminController(DoctorService doctorService){
        this.doctorService = doctorService;
    }

    @GetMapping("/admin")
    public String homeAdmin(Model model){

        List<Doctor> allDoctors = doctorService.getAllDoctors();

        model.addAttribute("allDoctors" , allDoctors);

        return "admin";
    }



}
