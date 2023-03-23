package com.aplicatie_donare_de_sange.centru_de_donare.service;

import com.aplicatie_donare_de_sange.centru_de_donare.Dto.DoctorDto;
import com.aplicatie_donare_de_sange.centru_de_donare.Model.Doctor;

import java.util.List;

public interface DoctorService {

    Doctor save(DoctorDto doctorDto);

    Doctor save(Doctor doctor);

    List<Doctor> getAllDoctors();

    void deleteDoctor(Long id);

    Doctor getOne(Long id);

    Doctor findByUsername(String username);

    void update(Doctor doctor);


}
