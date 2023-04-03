package com.aplicatie_donare_de_sange.centru_de_donare.service;

import com.aplicatie_donare_de_sange.centru_de_donare.Model.Donator;
import com.aplicatie_donare_de_sange.centru_de_donare.Model.Locatie;
import com.aplicatie_donare_de_sange.centru_de_donare.Model.Programare;
import com.aplicatie_donare_de_sange.centru_de_donare.Repository.ProgramariRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProgramariServiceImpl implements ProgramariService {

    private ProgramariRepository programariRepository;

    public ProgramariServiceImpl(ProgramariRepository programariRepository) {
        this.programariRepository = programariRepository;
    }

    @Override
    public Programare save(Donator donator, Locatie locatie, Date date) {

        // next time, și logica de programare pls
        Programare programare = new Programare(date, locatie, donator);
        return programariRepository.save(programare);

    }

    @Override
    public List<Programare> getAllProgramari() {

        return programariRepository.findAll();

    }

    @Override
    public void deleteProgramare(Long id) {

        programariRepository.deleteById(id);
    }

    @Override
    public Programare getOne(Long id) {
        return programariRepository.getOne(id);
    }

  /*  @Override
    public List<Programare> programariDonator(Donator donator) {

        return programariRepository.getProgramari(donator);
    }*/


    @Override
    public List<Programare> programariDonator(Donator curent) {

        List<Programare> programari = programariRepository.findAll();

        return programari.stream().filter(t -> t.getDonator().equals(curent)).collect(Collectors.toList());


    }

    @Override
    public List<Programare> getAllLocationProgram(Locatie locatie) {

        return getAllProgramari().stream().filter(t -> (t.getLocatie().equals(locatie)) && !t.isAnulata() && !t.isStatus()).collect(Collectors.toList());


    }

    @Override
    public void update(Programare p) {
        programariRepository.save(p);
    }

    @Override
    public List<Programare> getProgramariFinalizare(Donator curent) {


        return programariRepository.findAll().stream().filter(t -> (t.getDonator().equals(curent) && (t.isStatus() || t.isAnulata()))).collect(Collectors.toList());
    }

    @Override
    public List<Programare> getProgramariInitiate(Donator curent) {
        return programariRepository.findAll().stream().filter(t -> (t.getDonator().equals(curent) && !t.isStatus() && !t.isAnulata())).collect(Collectors.toList());

    }

    @Override
    public void anuleazaProgramarea(long id) {

        Programare p = programariRepository.getOne(id);

        p.setAnulata(true);

        programariRepository.save(p);

    }

    @Override
    public void modificaStarea(long id) {

        Programare p = programariRepository.getOne(id);

        p.setStatus(true);

        programariRepository.save(p);
    }

    @Override
    public void modificaStareaEsec(long id) {

        Programare p = programariRepository.getOne(id);

        p.setStatus(true);
        p.setAnulata(true);

        programariRepository.save(p);

    }

    @Override
    public List<Programare> getAll(Donator donator) {

        return programariRepository.findAll().stream().filter(t-> t.getDonator().equals(donator)).collect(Collectors.toList());

    }

    @Override
    public void deleteManyProgramari(List<Programare> allProgramari) {

        for(Programare p: allProgramari)
            programariRepository.delete(p);


    }
}
