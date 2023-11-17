package com.linknote.server.service;

import com.linknote.server.payload.Dto.CreateNoteRequest;
import com.linknote.server.payload.Dto.CreateNoteResponse;
import com.linknote.server.payload.Dto.NoteResponse;
import com.linknote.server.payload.Dto.UpdateNoteRequest;

import java.util.List;

public interface NoteService {
    CreateNoteResponse createNote(CreateNoteRequest createNoteRequest);

    List<NoteResponse> getAllNotes();

    NoteResponse getNoteById(Long id);

    void updateNoteById(UpdateNoteRequest updateNoteRequest);

    void deleteNoteById(String id);

}
