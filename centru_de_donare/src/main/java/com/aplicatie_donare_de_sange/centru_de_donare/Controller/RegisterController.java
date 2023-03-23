package com.aplicatie_donare_de_sange.centru_de_donare.Controller;

import com.aplicatie_donare_de_sange.centru_de_donare.Dto.DonatorDto;
import com.aplicatie_donare_de_sange.centru_de_donare.Model.Donator;
import com.aplicatie_donare_de_sange.centru_de_donare.service.DonatorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    private DonatorService donatorService;

    public RegisterController(DonatorService donatorService){
        this.donatorService = donatorService;
    }

    @GetMapping("/register")
    public String login(Model model)
    {
        return "register";
    }

    @ModelAttribute("donator")
    public DonatorDto userRegistrationDto() {
        return new DonatorDto();
    }

    @PostMapping("/register")
    public String registerUserAccount(@ModelAttribute("donator") DonatorDto registrationDto) {

        registrationDto.setRole("ROLE_DONATOR");
        registrationDto.setActivate(true);

        System.out.println(registrationDto);

        donatorService.save(registrationDto);
        return "redirect:/register?success";
    }


}
