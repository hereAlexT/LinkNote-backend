package com.cachenote.server.service.impl;

import com.cachenote.server.entity.NoteDoc;
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
    public NoteRequest createNote(NoteRequest noteRequest) {

        //convert DTO to entity
        NoteDoc noteDoc = new NoteDoc();
        noteDoc.setBody(noteRequest.getBody());
        NoteDoc newNoteDoc = noteRepository.save(noteDoc);

        logger.debug("Created NoteDoc: {}", newNoteDoc.toString());

        //convert entity to DTO
        NoteRequest newResponse = new NoteRequest();
        newResponse.setId(newNoteDoc.getId());
        newResponse.setBody(newNoteDoc.getBody());
        return newResponse;
    }

    @Override
    public List<NoteRequest> getAllNotes() {
        List<NoteDoc> noteDocs = noteRepository.findAll();
        List<NoteRequest> response = new ArrayList<>();

        for (NoteDoc noteDoc : noteDocs) {
            NoteRequest newNoteRequest = new NoteRequest(noteDoc.getId(), noteDoc.getBody());
            response.add(newNoteRequest);
        }
        return response;

    }



}
