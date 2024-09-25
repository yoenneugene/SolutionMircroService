package com.example.backend;

import com.example.backend.model.Note;
import com.example.backend.model.Patient;
import com.example.backend.repository.NoteRepository;
import com.example.backend.repository.PatientRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class DataInitializer {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private PatientRepository patientRepository;

    @PostConstruct
    public void init() {
        // Vérifie si des données existent déjà
        if (noteRepository.count() == 0 && patientRepository.count() > 0) {
            System.out.println("Initialisation des notes, car aucune note n'existe encore et il y a des patients dans la base de données.");

            // Récupère un patient par ID pour la démonstration
            Optional<Patient> patient = patientRepository.findById("66f3e562c1f3f55a54964034"); // Exemple avec l'ID fourni

            // Si le patient existe, ajouter des notes
            if (patient.isPresent()) {
                Patient p = patient.get();
                noteRepository.save(new Note(p.getId().toString(), p.getPrenom() + " " + p.getNom(), "Première note de test."));
                noteRepository.save(new Note(p.getId().toString(), p.getPrenom() + " " + p.getNom(), "Deuxième note de test."));
                System.out.println("Les notes de test ont été ajoutées pour le patient: " + p.getPrenom() + " " + p.getNom());
            } else {
                System.out.println("Le patient avec l'ID '66f3e562c1f3f55a54964034' n'existe pas dans la base de données.");
            }
        } else if (noteRepository.count() > 0) {
            System.out.println("Les notes existent déjà dans la base de données, aucune nouvelle note n'a été ajoutée.");
        } else if (patientRepository.count() == 0) {
            System.out.println("Aucun patient n'est présent dans la base de données. Les notes ne peuvent pas être ajoutées.");
        }
    }
}
