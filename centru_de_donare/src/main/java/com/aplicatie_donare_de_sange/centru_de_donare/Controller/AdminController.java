package com.aplicatie_donare_de_sange.centru_de_donare.Controller;

import com.aplicatie_donare_de_sange.centru_de_donare.Model.Doctor;
import com.aplicatie_donare_de_sange.centru_de_donare.service.DoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

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
