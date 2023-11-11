package com.cachenote.server.service;

import com.cachenote.server.payload.Reponse.NoteResponse;
import com.cachenote.server.payload.Request.NoteRequest;

import java.util.List;

public interface NoteService {
    NoteResponse createNote(NoteRequest noteRequest);

    List<NoteResponse> getAllNotes();

    NoteResponse getNoteById(Long id);

    void updateNoteById(NoteRequest noteRequest);

    void deleteNoteById(String id);

}
