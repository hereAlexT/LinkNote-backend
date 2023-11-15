package com.cachenote.server.service;

import com.cachenote.server.common.exception.NoteAccessDeniedException;
import com.cachenote.server.common.exception.NoteNotFoundException;
import com.cachenote.server.payload.reponse.NoteResponse;
import com.cachenote.server.payload.entity.Note;
import com.cachenote.server.payload.request.UpdateNoteRequest;
import com.cachenote.server.payload.entity.User;
import com.cachenote.server.repository.UserRepository;
import com.cachenote.server.security.entity.UserDetailsImpl;
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


    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    public NoteServiceImpl(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }


    @Override
    public NoteResponse createNote(UpdateNoteRequest updateNoteRequest) {

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        Long userId = userDetails.getId();

        // Create a reference to the User - no database call is made here
        User userReference = userRepository.getReferenceById(userId);

        //convert DTO to entity
        Note note = new Note();
        note.setBody(updateNoteRequest.getBody());
        note.setUser(userReference);
        Note newNote = noteRepository.save(note);
        logger.debug("Created Note: {}", newNote);

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

        Long userId = userDetails.getId();
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

        Long userId = userDetails.getId();
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException(id, null));

        // Check if the authenticated user is the owner of the note
        if (!note.getUser().getId().equals(userId)) {
            throw new NoteAccessDeniedException(userId, id, null);
        }

        return new NoteResponse(note.getId(), note.getBody());
    }

    @Override
    public void updateNoteById(UpdateNoteRequest updateNoteRequest) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        Long userId = userDetails.getId();

        Note existingNote = noteRepository.findById(updateNoteRequest.getId())
                .orElseThrow(() -> new NoteNotFoundException(updateNoteRequest.getId(), null));

        // Check if the authenticated user is the owner of the note
        if (!existingNote.getUser().getId().equals(userId)) {
            throw new NoteAccessDeniedException(userId, updateNoteRequest.getId(), null);
        }

        existingNote.setBody(updateNoteRequest.getBody());
        noteRepository.save(existingNote);
    }

    @Override
    public void deleteNoteById(String id) {
        //todo: delete note by id
    }

}
