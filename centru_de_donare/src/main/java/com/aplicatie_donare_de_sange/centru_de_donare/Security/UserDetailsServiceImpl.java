package com.aplicatie_donare_de_sange.centru_de_donare.Security;

import com.aplicatie_donare_de_sange.centru_de_donare.Model.User;
import com.aplicatie_donare_de_sange.centru_de_donare.Model.UserDetailsImpl;
import com.aplicatie_donare_de_sange.centru_de_donare.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username));

        user.orElseThrow(() -> new UsernameNotFoundException("Not found " + username));

        return user.map(UserDetailsImpl::new).get();

    }

}


//Autentificatorul cere de la mai multi provideri sa primeasca userii
// userDetails interface face legatura cu locatia unde se afla userii
// de fapt .
// poate face legatura cu un fis txt, o BD etc
// la final auth ar trebui sa primeasca configurarea data de UserService
// care la randul ei are nevoie de o implementare