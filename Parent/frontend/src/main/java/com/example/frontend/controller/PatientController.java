package com.example.frontend.controller;

import com.example.frontend.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Controller
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public String getPatients(Model model) {
        ResponseEntity<Patient[]> response = restTemplate.getForEntity("http://localhost:8081/api/patients", Patient[].class);
        model.addAttribute("patients", Arrays.asList(response.getBody()));
        return "patients";
    }   }