package com.aplicatie_donare_de_sange.centru_de_donare.Repository;

import com.aplicatie_donare_de_sange.centru_de_donare.Model.Doctor;
import com.aplicatie_donare_de_sange.centru_de_donare.Model.Donator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.print.Doc;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    public Doctor findByUsername(String username);

}
