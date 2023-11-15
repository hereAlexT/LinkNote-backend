package com.cachenote.server.service;

import com.cachenote.server.payload.Dto.CreateNoteResponse;
import com.cachenote.server.payload.Dto.NoteResponse;
import com.cachenote.server.payload.Dto.UpdateNoteRequest;

import java.util.List;

public interface NoteService {
    CreateNoteResponse createNote(UpdateNoteRequest updateNoteRequest);

    List<NoteResponse> getAllNotes();

    NoteResponse getNoteById(Long id);

    void updateNoteById(UpdateNoteRequest updateNoteRequest);

    void deleteNoteById(String id);

}
