package com.example.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notes") // Indique que cette classe correspond à une collection MongoDB
public class Note {

    @Id
    private String id; // ID auto-généré par MongoDB
    private String patid; // L'ID du patient auquel la note est associée
    private String patient; // Le nom du patient
    private String note; // Le contenu de la note

    // Constructeurs
    public Note() {
    }

    public Note(String patid, String patient, String note) {
        this.patid = patid;
        this.patient = patient;
        this.note = note;
    }

    // Getters et Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
}

