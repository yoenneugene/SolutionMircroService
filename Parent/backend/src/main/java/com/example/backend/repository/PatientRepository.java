package com.example.backend.repository;

import com.example.backend.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface PatientRepository extends MongoRepository<Patient, String> {
    // Tu peux ajouter des méthodes de requête personnalisées ici si nécessaire
}