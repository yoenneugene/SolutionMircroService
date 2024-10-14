package com.example.frontend.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Patient {

    private String id;
    private String prenom;

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    private String nom;
    private LocalDate dateNaissance;
    private  String genre ;
    private String adresse;
    private String telephone;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    public Patient(String prenom, String id, String nom, LocalDate dateNaissance, String adresse, String telephone) {
        this.prenom = prenom;
        this.id = id;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.adresse = adresse;
        this.telephone = telephone;
    }

    public Patient() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissanceFromString(String dateNaissance) {
        this.dateNaissance = dateNaissance != null ? LocalDate.parse(dateNaissance, formatter) : null;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}