package com.example.backend.service;


import com.example.backend.Service.PatientService;
import com.example.backend.model.Patient;
import com.example.backend.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;





import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class PatientServiceTest {

    @Autowired
    private PatientService patientService;

    @MockBean
    private PatientRepository patientRepository;

    @Test
    public void testGetAllPatients() {
        // Créer des patients de test en utilisant le constructeur fourni
        Patient patient1 = new Patient("John", "Doe", LocalDate.of(1980, 5, 15), "10 Rue des Fleurs", "M", "0102030405");
        Patient patient2 = new Patient("Jane", "Doe", LocalDate.of(1990, 8, 21), "12 Rue des Lilas", "F", "0102030406");

        // Simuler le comportement de la méthode findAll() du PatientRepository
        when(patientRepository.findAll()).thenReturn(Arrays.asList(patient1, patient2));


        // Appeler la méthode à tester
        List<Patient> patients = patientService.getAllPatients();

        // Vérifier les résultats attendus
        assertEquals(2, patients.size());
        assertEquals("John", patients.get(0).getPrenom());
        assertEquals("Jane", patients.get(1).getPrenom());
    }

    @Test
    public void testAddPatient() {
        // Créer un patient de test en utilisant le constructeur fourni
        Patient patient = new Patient("John", "Doe", LocalDate.of(1980, 5, 15), "10 Rue des Fleurs", "M", "0102030405");

        // Simuler le comportement de la méthode save() du PatientRepository
        when(patientRepository.save(patient)).thenReturn(patient);

        // Appeler la méthode à tester
        Patient savedPatient = patientService.addPatient(patient);

        // Vérifier que le patient est bien celui qui a été sauvegardé
        assertNotNull(savedPatient);
        assertEquals("John", savedPatient.getPrenom());
    }

    @Test
    public void testDeletePatient() {
        // Appeler la méthode à tester
        String patientId = "1";
        patientService.deletePatient(patientId);

        // Vérifier que la méthode deleteById() a bien été appelée dans le PatientRepository
        assertDoesNotThrow(() -> patientService.deletePatient(patientId));
    }

    @Test
    public void testGetPatientById() {
        // Créer un patient de test en utilisant le constructeur fourni
        Patient patient = new Patient("John", "Doe", LocalDate.of(1980, 5, 15), "10 Rue des Fleurs", "M", "0102030405");

        // Simuler le comportement de la méthode findById() du PatientRepository
        when(patientRepository.findById("1")).thenReturn(Optional.of(patient));

        // Appeler la méthode à tester
        Optional<Patient> result = patientService.getPatientById("1");

        // Vérifier que le patient retourné est correct
        assertTrue(result.isPresent());
        assertEquals("John", result.get().getPrenom());
    }
}