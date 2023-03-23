package com.aplicatie_donare_de_sange.centru_de_donare.service;

import com.aplicatie_donare_de_sange.centru_de_donare.Model.Doctor;
import com.aplicatie_donare_de_sange.centru_de_donare.Model.Locatie;
import com.aplicatie_donare_de_sange.centru_de_donare.Repository.LocatieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocatieServiceImpl implements LocatieService{

    private LocatieRepository locatieRepository;


    public LocatieServiceImpl(LocatieRepository locatieRepository){

        this.locatieRepository = locatieRepository;

    }


    @Override
    public List<Locatie> getAllLocatii() {

        return locatieRepository.findAll();

    }

    @Override
    public Locatie getOne(Long id) {
        return locatieRepository.getOne(id);
    }


}
