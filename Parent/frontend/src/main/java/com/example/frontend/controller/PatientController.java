package com.example.frontend.controller;

import com.example.frontend.model.Note;
import com.example.frontend.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller

public class PatientController {

    @Autowired
    private RestTemplate restTemplate;
    private final String GATEWAY_URL = "http://localhost:8080/api/patients";
    private final String NOTES_API_URL = "http://localhost:8080/api/notes";// Assure-toi que ce chemin correspond à ta configuration Gateway

    @GetMapping("/patients")
    public String getAllPatients(Model model) {
        // Envoyer une requête GET à travers le Gateway pour obtenir la liste des patients
        Patient[] patients = restTemplate.getForObject(GATEWAY_URL, Patient[].class);

        // Ajouter la liste des patients au modèle
        model.addAttribute("patients", Arrays.asList(patients));
        return "patients"; // Retourne la vue patients.html
    }
    @GetMapping("/patients/{id}")
    public String getPatientDetails(@PathVariable String id, Model model) {
        // Récupération des informations du patient via le Gateway
        Patient patient = restTemplate.getForObject("http://localhost:8080/api/patients/" + id, Patient.class);

        // Récupération des notes du patient via le Gateway
        Note[] notes = restTemplate.getForObject("http://localhost:8080/api/notes/patient/"+id, Note[].class);

        // Ajout des informations du patient au modèle
        model.addAttribute("patient", patient);

        // Ajout des notes au modèle
        model.addAttribute("notes", notes);

        return "patients-details"; // correspond au fichier patient-details.html
    }
    @GetMapping("/patients/{id}/add-note")
    public String showAddNoteForm(@PathVariable String id, Model model) {
        Patient patient = restTemplate.getForObject(GATEWAY_URL + "/" + id, Patient.class);
        model.addAttribute("patient", patient);
        return "add-note";
    }

    // Traite l'ajout de la note
    @PostMapping("/patients/{id}/add-note")
    public String addNote(@PathVariable String id, @RequestParam String note) {
        Patient patient = restTemplate.getForObject("http://localhost:8080/api/patients/" + id, Patient.class);
        Note newNote = new Note();
        newNote.setPatid(id);
        newNote.setPatient(patient.getPrenom() + " " + patient.getNom());
        newNote.setNote(note);

        restTemplate.postForObject(NOTES_API_URL, newNote, Note.class);
        return "redirect:/patients/" + id;
    }
}