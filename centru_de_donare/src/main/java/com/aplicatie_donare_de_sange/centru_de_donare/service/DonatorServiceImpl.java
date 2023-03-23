package com.aplicatie_donare_de_sange.centru_de_donare.service;

import com.aplicatie_donare_de_sange.centru_de_donare.Dto.DonatorDto;
import com.aplicatie_donare_de_sange.centru_de_donare.Model.Donator;
import com.aplicatie_donare_de_sange.centru_de_donare.Repository.DonatorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DonatorServiceImpl implements DonatorService{

    private DonatorRepository donatorRepository;

    public DonatorServiceImpl (DonatorRepository donatorRepository){
        this.donatorRepository  = donatorRepository;
    }


    @Override
    public Donator save(DonatorDto donatorDto) {
       Donator donator = new Donator(donatorDto.getNume() , donatorDto.getPrenume(), donatorDto.getGrupa() , donatorDto.getJudet() ,donatorDto.getUsername(), donatorDto.getPassword());
       return  donatorRepository.save(donator);
    }

    @Override
    public Donator findByUsername(String username) {

        return donatorRepository.findByUsername(username);
    }


    @Override
    public void deleteDonator(long id) {

        donatorRepository.deleteById(id);
    }

    @Override
    public void update(Donator donator) {

        donatorRepository.save(donator);
    }

    @Override
    public Optional<Donator> findById(long id) {
        return donatorRepository.findById(id);
    }

    @Override
    public Donator getOne(Long id) {

        return donatorRepository.getOne(id);

    }
}
