package com.example.backend.service;

import com.example.backend.Service.NoteService;
import com.example.backend.Service.RisqueService;
import com.example.backend.model.Note;
import com.example.backend.model.Patient;
import com.example.backend.repository.NoteRepository;
import com.example.backend.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class NoteServiceTest {

    @Autowired
    private NoteService noteService;

    @MockBean
    private NoteRepository noteRepository;

    @MockBean
    private PatientRepository patientRepository;

    @MockBean
    private RisqueService risqueService;

    private Patient patient;
    private Note note1;
    private Note note2;

    @BeforeEach
    public void setUp() {
        // Setup d'exemples de données
        patient = new Patient("John", "Doe", LocalDate.of(1985, 5, 15), "123 Main St", "M", "555-1234");
        note1 = new Note( "1", "John Doe", "Note sur la santé");
        note2 = new Note( "1", "John Doe", "Cholestérol élevé");

        // Mock des retours de noteRepository et patientRepository
        when(patientRepository.findById(Long.valueOf("1"))).thenReturn(Optional.of(patient));
        when(noteRepository.findByPatid("p1")).thenReturn(Arrays.asList(note1, note2));
    }

    @Test
    public void testGetAllNotes() {
        // Simuler le comportement du repository
        when(noteRepository.findAll()).thenReturn(Arrays.asList(note1, note2));

        // Tester la méthode du service
        List<Note> notes = noteService.getAllNotes();

        // Assertions
        assertEquals(2, notes.size());
        verify(noteRepository, times(1)).findAll();
    }

    @Test
    public void testAddNote() {
        // Simuler l'ajout de note et la mise à jour du risque
        when(noteRepository.save(any(Note.class))).thenReturn(note1);
        when(risqueService.assessRisk(any(Patient.class), anyList())).thenReturn("None");

        // Ajouter une note
      noteService.addNote(note1);

      


        // Vérifier que la méthode de mise à jour du risque a été appelée
        verify(noteRepository, times(1)).save(note1);

    }

    @Test
    public void testUpdateNote() {
        // Simuler la mise à jour de note et la mise à jour du risque
        when(noteRepository.save(any(Note.class))).thenReturn(note1);
        when(risqueService.assessRisk(any(Patient.class), anyList())).thenReturn("Low Risk");

        // Mettre à jour une note
        Note updatedNote = noteService.updateNote(note1);

        // Assertions


        // Vérifier que la méthode de mise à jour du risque a été appelée
        verify(noteRepository, times(1)).save(note1);

    }

             }

