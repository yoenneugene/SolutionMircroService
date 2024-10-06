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
                System.out.println("Aucun patient trouvé, ajout d'un nouveau patient.");

                // Créer et sauvegarder un nouveau patient avec le constructeur détaillé
                Patient nouveauPatient = new Patient(
                        "Jean",                            // Prenom
                        "Dupont",                          // Nom
                        LocalDate.of(1985, 5, 20),         // Date de naissance
                        "123 Rue de Paris, Paris",         // Adresse
                        "Homme",                           // Genre
                        "0123456789"                       // Téléphone
                );
                patientRepository.save(nouveauPatient);

                System.out.println("Nouveau patient ajouté : " + nouveauPatient.getPrenom() + " " + nouveauPatient.getNom());

                // Ajouter une note pour le nouveau patient
                noteRepository.save(new Note(nouveauPatient.getId().toString(), "none", "Première note pour le nouveau patient."));
                System.out.println("Première note ajoutée pour le nouveau patient.");
            } else {
                System.out.println("Des patients existent déjà dans la base de données.");

                // Récupérer tous les patients
                List<Patient> patients = patientRepository.findAll();

                // Choisir un patient aléatoirement
                if (!patients.isEmpty()) {
                    Random random = new Random();
                    Patient p = patients.get(random.nextInt(patients.size()));

                    // Ajouter des notes de test pour ce patient
                    noteRepository.save(new Note(p.getId().toString(), "none", "Première note de test."));
                    System.out.println("Note ajoutée pour le patient: " + p.getPrenom() + " " + p.getNom());
                } else {
                    System.out.println("Erreur: Aucun patient n'est présent dans la base de données.");
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de l'initialisation: " + e.getMessage());
        }
    }
}
