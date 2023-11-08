package com.cachenote.server.service.impl;

import com.cachenote.server.entity.Note;
import com.cachenote.server.payload.NoteDto;
import com.cachenote.server.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cachenote.server.repository.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class NoteServiceImpl implements NoteService {

    private static final Logger logger = LoggerFactory.getLogger(NoteServiceImpl.class);


    private NoteRepository noteRepository;

    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }


    @Override
    public NoteDto createNote(NoteDto noteDto) {

        //convert DTO to entity
        Note note = new Note();
        note.setBody(noteDto.getBody());
        Note newNote = noteRepository.save(note);

        logger.debug("Created Note: {}", newNote.toString());

        //convert entity to DTO
        NoteDto newResponse = new NoteDto();
        newResponse.setId(newNote.getId());
        newResponse.setBody(newNote.getBody());
        return newResponse;
    }


}
