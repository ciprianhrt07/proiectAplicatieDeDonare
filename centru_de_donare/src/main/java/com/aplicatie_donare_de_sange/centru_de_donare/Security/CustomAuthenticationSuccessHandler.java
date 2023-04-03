package com.aplicatie_donare_de_sange.centru_de_donare.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    SimpleUrlAuthenticationSuccessHandler donatorSuccessHandler =
            new SimpleUrlAuthenticationSuccessHandler("/donator");
    SimpleUrlAuthenticationSuccessHandler adminSuccessHandler =
            new SimpleUrlAuthenticationSuccessHandler("/admin");
    SimpleUrlAuthenticationSuccessHandler doctorSuccessHandler =
            new SimpleUrlAuthenticationSuccessHandler("/doctor/info");


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        // vorbim cred că peste 2 laboratoare viitoare despre roluri si granturi, pls amintește-mi
        for (final GrantedAuthority grantedAuthority : authorities) {
            String authorityName = grantedAuthority.getAuthority();
            if (authorityName.equals("ROLE_ADMIN")) {
                // if the user is an ADMIN delegate to the adminSuccessHandler
                this.adminSuccessHandler.onAuthenticationSuccess(request, response, authentication);
                return;
            }else
            if (authorityName.equals("ROLE_DOCTOR")) {
                // if the user is an DOCTOR delegate to the adminSuccessHandler
                // copy paste error? ^
                this.doctorSuccessHandler.onAuthenticationSuccess(request, response, authentication);
                return;
            }
        }
        // if the user is not an admin delegate to the userSuccessHandler
        this.donatorSuccessHandler.onAuthenticationSuccess(request, response, authentication);
    }
}