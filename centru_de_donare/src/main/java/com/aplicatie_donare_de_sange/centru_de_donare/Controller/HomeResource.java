package com.aplicatie_donare_de_sange.centru_de_donare.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeResource {

    @GetMapping("")
    public String home(){


       // String currentPrincipalName = User
        //System.out.println(currentPrincipalName);


        return "redirect:/login";
    }

    // rămășițe?
    @GetMapping("/user")
    public String homeUser(){

        return "<h1> WELCOME USER </h1>";
    }

    @GetMapping("/logout")
    public String paginaPrincipala(){
        return "login";
    }



}
