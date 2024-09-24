package com.example.frontend.controller;

import com.example.frontend.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller

public class PatientController {

    @Autowired
    private RestTemplate restTemplate;
    private final String GATEWAY_URL = "http://localhost:8080/api/patients";  // Assure-toi que ce chemin correspond à ta configuration Gateway

    @GetMapping("/patients")
    public String getAllPatients(Model model) {
        // Envoyer une requête GET à travers le Gateway pour obtenir la liste des patients
        Patient[] patients = restTemplate.getForObject(GATEWAY_URL, Patient[].class);

        // Ajouter la liste des patients au modèle
        model.addAttribute("patients", Arrays.asList(patients));
        return "patients"; // Retourne la vue patients.html
    }
    @GetMapping("/patients/{id}")
    public String getPatientDetails(@PathVariable Long id, Model model) {
        Patient patient = restTemplate.getForObject("http://localhost:8080/api/patients/" + id, Patient.class);
        model.addAttribute("patient", patient);
        return "patients-details"; // correspond au fichier patient-details.html
    }}