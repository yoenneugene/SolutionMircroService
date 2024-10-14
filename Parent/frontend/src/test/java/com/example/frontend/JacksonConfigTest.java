package com.example.frontend;

import com.example.frontend.model.Patient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JacksonConfigTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testObjectMapperIsNotNull() {
        // Vérifie que l'ObjectMapper est bien injecté
        assertNotNull(objectMapper, "ObjectMapper should not be null");
    }
    @Test
    public void testLocalDateSerialization() throws Exception {
        // Création d'une date à tester
        LocalDate date = LocalDate.now();

        // Sérialisation de la date
        String serializedDate = objectMapper.writeValueAsString(date);

        // Désérialisation de la date
        LocalDate deserializedDate = objectMapper.readValue(serializedDate, LocalDate.class);

        // Vérifie que la désérialisation a produit la même date
        assertTrue(date.isEqual(deserializedDate), "Serialized and deserialized dates should be equal");
    }
    

}
