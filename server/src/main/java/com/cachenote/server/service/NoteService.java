package com.cachenote.server.service;

import com.cachenote.server.payload.reponse.NoteResponse;
import com.cachenote.server.payload.request.UpdateNoteRequest;

import java.util.List;

public interface NoteService {
    NoteResponse createNote(UpdateNoteRequest updateNoteRequest);

    List<NoteResponse> getAllNotes();

    NoteResponse getNoteById(Long id);

    void updateNoteById(UpdateNoteRequest updateNoteRequest);

    void deleteNoteById(String id);

}
