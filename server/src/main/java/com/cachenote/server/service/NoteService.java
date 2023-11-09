package com.cachenote.server.service;

import com.cachenote.server.payload.Request.NoteRequest;

import java.util.List;

public interface NoteService {
    NoteRequest createNote(NoteRequest noteRequest);

    List<NoteRequest> getAllNotes();

//    NoteDto getNoteById(String id);
//
//    NoteDto updateNote(String id);
//
//    void deletePostById(String id);


}
