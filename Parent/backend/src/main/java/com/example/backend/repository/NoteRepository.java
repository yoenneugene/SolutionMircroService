package com.example.backend.repository;

import com.example.backend.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {
    List<Note> findByPatid(String patid); // Permet de récupérer les notes par l'ID du patient
}