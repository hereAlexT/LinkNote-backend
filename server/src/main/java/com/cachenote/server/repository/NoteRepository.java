package com.cachenote.server.repository;


import com.cachenote.server.entity.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


public interface NoteRepository extends MongoRepository<Note, String> {


    @Query("{_id: '?0'}")
    public Note findNoteById(String id);


}
