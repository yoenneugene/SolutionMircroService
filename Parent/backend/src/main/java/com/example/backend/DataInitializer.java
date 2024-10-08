package com.example.backend;

import com.example.backend.model.Note;
import com.example.backend.model.Patient;
import com.example.backend.repository.NoteRepository;
import com.example.backend.repository.PatientRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component
public class DataInitializer {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private PatientRepository patientRepository;

    @PostConstruct
    public void init() {
        try {
            // Vérifie si des patients existent dans la base de données
            if (patientRepository.count() == 0) {
                System.out.println("Aucun patient trouvé, ajout de nouveaux patients.");

                // Créer et sauvegarder trois patients
                Patient patient1 = new Patient(
                        "Jean",
                        "Dupont",
                        LocalDate.of(1985, 5, 20),
                        "123 Rue de Paris, Paris",
                        "Homme",
                        "0123456789"
                );
                Patient patient2 = new Patient(
                        "Marie",
                        "Curie",
                        LocalDate.of(1990, 3, 15),
                        "456 Rue de Lyon, Lyon",
                        "Femme",
                        "0987654321"
                );
                Patient patient3 = new Patient(
                        "Paul",
                        "Martin",
                        LocalDate.of(2000, 11, 30),
                        "789 Rue de Lille, Lille",
                        "Homme",
                        "0147258369"
                );

                // Sauvegarde des patients
                patientRepository.save(patient1);
                patientRepository.save(patient2);
                patientRepository.save(patient3);

                System.out.println("Nouveaux patients ajoutés.");

                // Ajout des notes pour chaque patient avec suffisamment de déclencheurs
                noteRepository.save(new Note(patient1.getId().toString(), "none", "Patient stable sans déclencheur. Aucune mention de fumeur, hémoglobine a1c ou autres facteurs."));
                noteRepository.save(new Note(patient2.getId().toString(), "borderline", "Patient présente une hémoglobine a1c élevée et a aussi des vertiges. Risque de cholestérol."));
                noteRepository.save(new Note(patient3.getId().toString(), "in danger", "Le patient montre des signes de rechute, vertiges, réaction allergique et un poids anormal."));

                System.out.println("Notes ajoutées pour chaque patient.");




            } else {
                System.out.println("Des patients existent déjà dans la base de données.");
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de l'initialisation: " + e.getMessage());
        }
    }
}
