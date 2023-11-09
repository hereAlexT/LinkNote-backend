package com.cachenote.server.service.impl;

import com.cachenote.server.common.exception.NoteNotFoundException;
import com.cachenote.server.payload.Reponse.NoteResponse;
import com.cachenote.server.payload.entity.NoteDoc;
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
        NoteDoc noteDoc = new NoteDoc();
        noteDoc.setBody(noteRequest.getBody());
        NoteDoc newNoteDoc = noteRepository.save(noteDoc);

        logger.debug("Created NoteDoc: {}", newNoteDoc.toString());

        //convert entity to DTO
        NoteResponse newResponse = new NoteResponse();
        newResponse.setId(newNoteDoc.getId());
        newResponse.setBody(newNoteDoc.getBody());
        return newResponse;
    }

    @Override
    public List<NoteResponse> getAllNotes() {
        List<NoteDoc> noteDocs = noteRepository.findAll();


        List<NoteResponse> response = new ArrayList<>();
        for (NoteDoc noteDoc : noteDocs) {
            NoteResponse newNoteResponse = new NoteResponse(noteDoc.getId(), noteDoc.getBody());
            response.add(newNoteResponse);
        }
        return response;

    }

    @Override
    public NoteResponse getNoteById(String id) {
        NoteDoc noteDoc = noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException(id));

        return new NoteResponse(noteDoc.getId(), noteDoc.getBody());
    }

    @Override
    public void updateNoteById(NoteRequest noteRequest) {
        NoteDoc existingNote = noteRepository.findById(noteRequest.getId())
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
