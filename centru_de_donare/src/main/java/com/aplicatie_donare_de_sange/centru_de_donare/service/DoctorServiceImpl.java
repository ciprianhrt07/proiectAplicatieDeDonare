package com.aplicatie_donare_de_sange.centru_de_donare.service;

import com.aplicatie_donare_de_sange.centru_de_donare.Dto.DoctorDto;
import com.aplicatie_donare_de_sange.centru_de_donare.Model.Doctor;
import com.aplicatie_donare_de_sange.centru_de_donare.Model.Locatie;
import com.aplicatie_donare_de_sange.centru_de_donare.Repository.DoctorRepository;
import com.aplicatie_donare_de_sange.centru_de_donare.Repository.LocatieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService{


    private DoctorRepository doctorRepository;
    private LocatieRepository locatieRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository , LocatieRepository locatieRepository){
        this.doctorRepository = doctorRepository;
        this.locatieRepository = locatieRepository;
    }

    @Override
    public Doctor save(DoctorDto doctorDto) {

        Long identificatorLocatie = Long.parseLong(doctorDto.getLocatie());

        Locatie locatie = locatieRepository.getOne(identificatorLocatie);

        //System.out.println(locatie);

        Doctor doctor = new Doctor(doctorDto.getUsername() , doctorDto.getPassword() , doctorDto.getNume(),doctorDto.getPrenume(),doctorDto.getSpecializare(),locatie);

        System.out.println("A fost introdus un nou doctor:");
        System.out.println(doctor.toString());

        return doctorRepository.save(doctor);

    }

    @Override
    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }


    @Override
    public List<Doctor> getAllDoctors(){

        return doctorRepository.findAll();

    }

    @Override
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

    @Override
    public Doctor getOne(Long id) {
       return doctorRepository.getOne(id);
    }

    @Override
    public Doctor findByUsername(String username) {

        return doctorRepository.findByUsername(username);
    }

    @Override
    public void update(Doctor doctor) {

        doctorRepository.save(doctor);

    }




}
