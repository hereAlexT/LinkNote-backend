package com.linknote.server.repository;


import com.linknote.server.payload.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findAllNotesByUserId(Long userId);
}