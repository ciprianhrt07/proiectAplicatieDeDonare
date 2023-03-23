package com.aplicatie_donare_de_sange.centru_de_donare.service;

import com.aplicatie_donare_de_sange.centru_de_donare.Dto.DonatorDto;
import com.aplicatie_donare_de_sange.centru_de_donare.Model.Donator;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface DonatorService {

    Donator save(DonatorDto donatorDto);

    Donator findByUsername(String username);
    void deleteDonator(long id);

    void update(Donator donator);

    Optional<Donator> findById(long id);

    Donator getOne(Long id);
}
