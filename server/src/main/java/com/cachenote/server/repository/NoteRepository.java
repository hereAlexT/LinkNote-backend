package com.cachenote.server.repository;


import com.cachenote.server.payload.entity.NoteDoc;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


public interface NoteRepository extends MongoRepository<NoteDoc, String> {


    @Query("{_id: '?0'}")
    public NoteDoc findNoteById(String id);


}
