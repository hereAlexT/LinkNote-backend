package com.cachenote.server.service.impl;

import com.cachenote.server.common.exception.NoteAccessDeniedException;
import com.cachenote.server.common.exception.NoteNotFoundException;
import com.cachenote.server.payload.Reponse.NoteResponse;
import com.cachenote.server.payload.entity.Note;
import com.cachenote.server.payload.Request.NoteRequest;
import com.cachenote.server.payload.entity.User;
import com.cachenote.server.repository.UserRepository;
import com.cachenote.server.security.entity.UserDetailsImpl;
import com.cachenote.server.service.NoteService;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private UserRepository userRepository;

    public NoteServiceImpl(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }


    @Override
    public NoteResponse createNote(NoteRequest noteRequest) {

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        Long userId = userDetails.getIdLong();

        // Create a reference to the User - no database call is made here
        User userReference = userRepository.getReferenceById(userId);

        //convert DTO to entity
        Note note = new Note();
        note.setBody(noteRequest.getBody());
        note.setUser(userReference);
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
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        Long userId = userDetails.getIdLong();
        List<Note> notes = noteRepository.findAllNotesByUserId(userId); // Pass the list to the method

        List<NoteResponse> response = new ArrayList<>();
        for (Note note : notes) {
            NoteResponse newNoteResponse = new NoteResponse(note.getId(), note.getBody());
            response.add(newNoteResponse);
        }
        return response;
    }


    @Override
    public NoteResponse getNoteById(Long id) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        Long userId = userDetails.getIdLong();
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException(id, null));

        // Check if the authenticated user is the owner of the note
        if (!note.getUser().getId().equals(userId)) {
            throw new NoteAccessDeniedException(userId, id, null);
        }

        return new NoteResponse(note.getId(), note.getBody());
    }

    @Override
    public void updateNoteById(NoteRequest noteRequest) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        Long userId = userDetails.getIdLong();

        Note existingNote = noteRepository.findById(noteRequest.getId())
                .orElseThrow(() -> new NoteNotFoundException(noteRequest.getId(), null));

        // Check if the authenticated user is the owner of the note
        if (!existingNote.getUser().getId().equals(userId)) {
            throw new NoteAccessDeniedException(userId, noteRequest.getId(), null);
        }

        existingNote.setBody(noteRequest.getBody());
        noteRepository.save(existingNote);
    }

    @Override
    public void deleteNoteById(String id) {
        //todo: delete note by id
    }

}
