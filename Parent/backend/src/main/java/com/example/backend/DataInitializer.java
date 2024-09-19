package com.example.backend;

import com.example.backend.model.Patient;
import com.example.backend.repository.PatientRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer {

    @Autowired
    private PatientRepository patientRepository;

    @PostConstruct
    public void init() {
        // Vérifie si des données existent déjà pour éviter les doublons
        if (patientRepository.count() == 0) {
            patientRepository.save(new Patient("Jean", "Dupont", LocalDate.parse("1980-05-15"), "10 Rue des Fleurs, Paris", "0102030405"));
            patientRepository.save(new Patient("Marie", "Durand", LocalDate.parse("1992-09-22"), "25 Avenue des Champs, Lyon", "0607080900"));
            patientRepository.save(new Patient("Paul", "Martin", LocalDate.parse("1975-12-01"), "7 Boulevard Saint-Germain, Bordeaux", "0203040506"));
        }
    }
}