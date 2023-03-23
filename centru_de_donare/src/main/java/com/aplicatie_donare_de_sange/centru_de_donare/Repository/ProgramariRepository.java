package com.aplicatie_donare_de_sange.centru_de_donare.Repository;

import com.aplicatie_donare_de_sange.centru_de_donare.Model.Donator;
import com.aplicatie_donare_de_sange.centru_de_donare.Model.Programare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProgramariRepository extends JpaRepository<Programare, Long> {

   /* @Query( "SELECT p FROM Programare as p where p.id = : #{#donator.id}")
    List<Programare> getProgramari(@Param("donator") Donator donator);*/
}
