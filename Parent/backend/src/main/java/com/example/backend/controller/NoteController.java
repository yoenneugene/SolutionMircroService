package com.example.backend.controller;

import com.example.backend.Service.NoteService;
import com.example.backend.model.Note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    // Récupérer toutes les notes
    @GetMapping
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    // Récupérer toutes les notes d'un patient spécifique
    @GetMapping("/patient/{patid}")
    public List<Note> getNotesByPatientId(@PathVariable String patid) {
        return noteService.getNotesByPatid(patid);
    }

    // Récupérer une note par ID
    @GetMapping("/{id}")
    public Optional<Note> getNoteById(@PathVariable String id) {
        return noteService.getNoteById(id);
    }

    // Ajouter une nouvelle note
    @PostMapping
    public Note addNote(@RequestBody Note note) {
        return noteService.addNote(note);
    }

    // Supprimer une note
    @DeleteMapping("/{id}")
    public void deleteNoteById(@PathVariable String id) {
        noteService.deleteNoteById(id);
    }
}
