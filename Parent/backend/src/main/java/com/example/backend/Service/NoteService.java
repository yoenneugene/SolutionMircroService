package com.example.backend.Service;

import com.example.backend.model.Note;
import com.example.backend.model.Patient;
import com.example.backend.repository.NoteRepository;
import com.example.backend.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private RisqueService risqueService ;

    // Récupérer toutes les notes
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    // Récupérer les notes d'un patient par patid
    public List<Note> getNotesByPatid(String patid) {
        return noteRepository.findByPatid(patid);
    }

    // Ajouter une nouvelle note et mettre à jour le risque dans le champ "patient"
    public Note addNote(Note note) {
        // Sauvegarder la note
        Note savedNote = noteRepository.save(note);

        // Après avoir ajouté une nouvelle note, réévaluer le risque
        updatePatientRiskInNotes(note.getPatid());

        return savedNote;
    }

    public Note updateNote(Note note) {
        // Sauvegarder les modifications de la note
        Note updatedNote = noteRepository.save(note);

        // Après avoir modifié la note, réévaluer le risque
        updatePatientRiskInNotes(note.getPatid());

        return updatedNote;
    }
    // Supprimer une note par son ID
    public void deleteNoteById(String id) {
        // Récupérer la note avant de la supprimer pour récupérer l'ID du patient
        Optional<Note> note = noteRepository.findById(id);
        if (note.isPresent()) {
            String patid = note.get().getPatid();

            // Supprimer la note
            noteRepository.deleteById(id);

            // Réévaluer le risque après suppression de la note
            updatePatientRiskInNotes(patid);
        }
    }
    private void updatePatientRiskInNotes(String patientId) {
        // Récupérer toutes les notes du patient
        List<Note> patientNotes = noteRepository.findByPatid(patientId);

        // Récupérer les informations du patient (si besoin pour le genre et l'âge)
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        if (patientOptional.isPresent()) {
            Patient patient = patientOptional.get();

            // Évaluer le risque à partir de toutes les notes
            String risk = risqueService.assessRisk(patientOptional.get(),patientNotes);

            // Mettre à jour le champ "patient" (qui contient le risque) dans chaque note
            for (Note note : patientNotes) {
                note.setPatient(risk);  // Met à jour le champ avec le risque
                noteRepository.save(note);  // Sauvegarder les modifications
            }
        }
    }

    // Récupérer une note par son ID
    public Optional<Note> getNoteById(String id) {
        return noteRepository.findById(id);
    }
    public List<Note> getNotesByPatientId(String patientId) {
        return noteRepository.findByPatid(patientId);
    }
}
