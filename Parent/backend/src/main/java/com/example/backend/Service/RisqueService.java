package com.example.backend.Service;

import com.example.backend.model.Note;
import com.example.backend.model.Patient;
import com.example.backend.repository.NoteRepository;
import com.example.backend.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class RisqueService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private PatientRepository patientRepository;

    // Liste des déclencheurs
    private static final List<String> TRIGGER_TERMS = Arrays.asList(
            "hémoglobine a1c", "microalbumine", "taille", "poids", "fumeur", "fumeuse", "anormal",
            "cholestérol", "vertiges", "rechute", "réaction", "anticorps"
    );

    // Méthode pour analyser les risques d'un patient
    public String assessRisk(Patient patient, List<Note> notes) {
        int triggerCount = 0;

        // Compter le nombre de déclencheurs présents dans toutes les notes
        for (Note note : notes) {
            for (String trigger : TRIGGER_TERMS) {
                if (note.getNote().contains(trigger)) {
                    triggerCount++;
                }
            }
        }

        // Déterminer le risque en fonction du nombre de déclencheurs et des infos du patient
        if (triggerCount == 0) {
            return "None";  // Aucun risque
        } else if (triggerCount >= 2 && triggerCount <= 5 && patient.getDateNaissance().isBefore(LocalDate.now().minusYears(30))) {
            return "Borderline";  // Risque limité
        } else if (patient.getGenre().equals("Homme") && patient.getDateNaissance().isAfter(LocalDate.now().minusYears(30)) && triggerCount >= 3 && triggerCount <=5) {
            return "In Danger";  // Danger pour les hommes de moins de 30 ans
        } else if (patient.getGenre().equals("Femme") && patient.getDateNaissance().isAfter(LocalDate.now().minusYears(30)) && triggerCount >= 4 && triggerCount <=7) {
            return "In Danger";
        } else if (triggerCount >= 6 && triggerCount <= 7 && patient.getDateNaissance().isBefore(LocalDate.now().minusYears(30))) {
            return "In danger"; // 
        } else if (triggerCount >= 5 && patient.getGenre().equals("Homme") && patient.getDateNaissance().isAfter(LocalDate.now().minusYears(30))) {
            return "Early Onset";  // Apparition précoce pour les hommes
        } else if (triggerCount >= 7 && patient.getGenre().equals("Femme") && patient.getDateNaissance().isAfter(LocalDate.now().minusYears(30))) {
            return "Early Onset";  // Apparition précoce pour les femmes
        } else if (triggerCount >= 8) {
            return "Early Onset";  // Apparition précoce pour les plus de 30 ans
        }

        return "None";  // Aucun risque par défaut
    }
}

    

