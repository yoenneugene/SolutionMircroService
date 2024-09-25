package com.example.frontend.model;


public class Note {
    private String id;
    private String patid; // ID du patient
    private String patient; // Nom du patient
    private String note; // Contenu de la note

    // Constructeur
    public Note(String patid, String patient, String note) {
        this.patid = patid;
        this.patient = patient;
        this.note = note;
    }

    public Note() {

    }

    // Getters et Setters
    public String getPatid() {
        return patid;
    }

    public void setPatid(String patid) {
        this.patid = patid;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
