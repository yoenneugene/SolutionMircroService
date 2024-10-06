package com.example.backend.service;

import com.example.backend.Service.RisqueService;
import com.example.backend.model.Note;
import com.example.backend.model.Patient;
import com.example.backend.repository.NoteRepository;
import com.example.backend.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RisqueServiceTest {

    @InjectMocks
    private RisqueService risqueService;

    @MockBean
    private NoteRepository noteRepository;

    @MockBean
    private PatientRepository patientRepository;

    private Patient patientMaleUnder30;
    private Patient patientMaleOver30;
    private Patient patientFemaleUnder30;
    private Patient patientFemaleOver30;

    @BeforeEach
    public void setUp() {
        patientMaleUnder30 = new Patient("Jean", "Dupont", LocalDate.now().minusYears(25), "123 Rue", "Homme", "0102030405");
        patientMaleOver30 = new Patient("Paul", "Martin", LocalDate.now().minusYears(35), "456 Rue", "Homme", "0102030406");
        patientFemaleUnder30 = new Patient("Marie", "Curie", LocalDate.now().minusYears(20), "789 Rue", "Femme", "0102030407");
        patientFemaleOver30 = new Patient("Claire", "Zhou", LocalDate.now().minusYears(32), "101 Rue", "Femme", "0102030408");
    }

    @Test
    public void testAssessRisk_NoTriggers() {
        List<Note> notes = Arrays.asList(new Note( "p1", "Jean Dupont", "Pas de déclencheurs ici."));
        String risk = risqueService.assessRisk(patientMaleOver30, notes);
        assertEquals("None", risk);
    }

    @Test
    public void testAssessRisk_BorderlineRisk() {
        List<Note> notes = Arrays.asList(
                new Note( "p1", "Jean Dupont", "hémoglobine a1c à vérifier."),
                new Note( "p1", "Jean Dupont", "cholestérol à surveiller.")
        );
        String risk = risqueService.assessRisk(patientMaleOver30, notes);
        assertEquals("Borderline", risk);
    }

    @Test
    public void testAssessRisk_InDanger_MaleUnder30() {
        List<Note> notes = Arrays.asList(
                new Note( "p1", "Jean Dupont", "hémoglobine a1c anormale."),
                new Note( "p1", "Jean Dupont", "cholestérol élevé."),
                new Note( "p1", "Jean Dupont", "vertiges fréquents.")
        );
        String risk = risqueService.assessRisk(patientMaleUnder30, notes);
        assertEquals("In Danger", risk);
    }

    @Test
    public void testAssessRisk_InDanger_FemaleUnder30() {
        List<Note> notes = Arrays.asList(
                new Note( "p1", "Marie Curie", "hémoglobine A1C anormale."),
                new Note( "p1", "Marie Curie", "cholestérol élevé."),
                new Note( "p1", "Marie Curie", "vertiges fréquents."),
                new Note( "p1", "Marie Curie", "réaction allergique.")
        );
        String risk = risqueService.assessRisk(patientFemaleUnder30, notes);
        assertEquals("In Danger", risk);
    }

    @Test
    public void testAssessRisk_EarlyOnset_Male() {
        List<Note> notes = Arrays.asList(
                new Note( "p1", "Paul Martin", "hémoglobine a1c anormale."),
                new Note( "p1", "Paul Martin", "cholestérol élevé."),
                new Note( "p1", "Paul Martin", "vertiges fréquents."),
                new Note("p1", "Paul Martin", "anormal."),
                new Note( "p1", "Paul Martin", "rechute."),
                new Note("p1", "Paul Martin", "fumeur."),
                new Note( "p1", "Paul Martin", "anticorps élevés.")
        );
        String risk = risqueService.assessRisk(patientMaleOver30, notes);
        assertEquals("Early Onset", risk);
    }

    @Test
    public void testAssessRisk_EarlyOnset_Female() {
        List<Note> notes = Arrays.asList(
                new Note( "p1", "Claire Zhou", "hémoglobine a1c anormale."),
                new Note("p1", "Claire Zhou", "cholestérol élevé."),
                new Note( "p1", "Claire Zhou", "vertiges fréquents."),
                new Note( "p1", "Claire Zhou", "anormal."),
                new Note( "p1", "Claire Zhou", "rechute."),
                new Note( "p1", "Claire Zhou", "fumeuse."),
                new Note( "p1", "Claire Zhou", "anticorps élevés."),
                new Note( "p1", "Claire Zhou", "microalbumine.")
        );
        String risk = risqueService.assessRisk(patientFemaleOver30, notes);
        assertEquals("Early Onset", risk);
    }
}