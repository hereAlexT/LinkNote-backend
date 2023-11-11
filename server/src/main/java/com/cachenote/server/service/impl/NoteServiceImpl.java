package com.cachenote.server.service.impl;

import com.cachenote.server.common.exception.NoteNotFoundException;
import com.cachenote.server.payload.Reponse.NoteResponse;
import com.cachenote.server.payload.entity.Note;
import com.cachenote.server.payload.Request.NoteRequest;
import com.cachenote.server.service.NoteService;
import org.springframework.stereotype.Service;
import com.cachenote.server.repository.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


@Service
public class NoteServiceImpl implements NoteService {

    private static final Logger logger = LoggerFactory.getLogger(NoteServiceImpl.class);


    private NoteRepository noteRepository;

    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }


    @Override
    public NoteResponse createNote(NoteRequest noteRequest) {

        //convert DTO to entity
        Note note = new Note();
        note.setBody(noteRequest.getBody());
        Note newNote = noteRepository.save(note);

        logger.debug("Created Note: {}", newNote.toString());

        //convert entity to DTO
        NoteResponse newResponse = new NoteResponse();
        newResponse.setId(newNote.getId());
        newResponse.setBody(newNote.getBody());
        return newResponse;
    }

    @Override
    public List<NoteResponse> getAllNotes() {
        List<Note> notes = noteRepository.findAll();


        List<NoteResponse> response = new ArrayList<>();
        for (Note note : notes) {
            NoteResponse newNoteResponse = new NoteResponse(note.getId(), note.getBody());
            response.add(newNoteResponse);
        }
        return response;

    }

    @Override
    public NoteResponse getNoteById(Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException(id));

        return new NoteResponse(note.getId(), note.getBody());
    }

    @Override
    public void updateNoteById(NoteRequest noteRequest) {
        Note existingNote = noteRepository.findById(noteRequest.getId())
                .map(note -> {
                    note.setBody(noteRequest.getBody());
                    return note;
                })
                .orElseThrow(() -> new NoteNotFoundException(noteRequest.getId()));

        noteRepository.save(existingNote);
    }

    @Override
    public void deleteNoteById(String id) {

    }

}
