package com.aplicatie_donare_de_sange.centru_de_donare.service;

import com.aplicatie_donare_de_sange.centru_de_donare.Model.Donator;
import com.aplicatie_donare_de_sange.centru_de_donare.Model.Locatie;
import com.aplicatie_donare_de_sange.centru_de_donare.Model.Programare;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ProgramariService {

    Programare save(Donator donator, Locatie locatie, Date date);

    List<Programare> getAllProgramari();

    void deleteProgramare(Long id);

    Programare getOne(Long id);

    List<Programare> programariDonator(Donator donator);

    List<Programare> getAllLocationProgram(Locatie locatie);

    void update(Programare p);

    List<Programare> getProgramariFinalizare(Donator curent);

    List<Programare> getProgramariInitiate(Donator curent);

    void anuleazaProgramarea(long id);

    void modificaStarea(long id);

    void modificaStareaEsec(long id);

    List<Programare> getAll(Donator donator);

    void deleteManyProgramari(List<Programare> allProgramari);
}
