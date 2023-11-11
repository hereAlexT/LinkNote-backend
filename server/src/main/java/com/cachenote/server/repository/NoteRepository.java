package com.cachenote.server.repository;


import com.cachenote.server.payload.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface NoteRepository extends JpaRepository<Note, Long> {
}