package com.example.backend.Service;

import com.example.backend.model.Note;
import com.example.backend.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    // Récupérer toutes les notes
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    // Récupérer les notes d'un patient par patid
    public List<Note> getNotesByPatid(String patid) {
        return noteRepository.findByPatid(patid);
    }

    // Ajouter une nouvelle note
    public Note addNote(Note note) {
        return noteRepository.save(note);
    }

    // Supprimer une note par son ID
    public void deleteNoteById(String id) {
        noteRepository.deleteById(id);
    }

    // Récupérer une note par son ID
    public Optional<Note> getNoteById(String id) {
        return noteRepository.findById(id);
    }
    public List<Note> getNotesByPatientId(String patientId) {
        return noteRepository.findByPatid(patientId);
    }
}
