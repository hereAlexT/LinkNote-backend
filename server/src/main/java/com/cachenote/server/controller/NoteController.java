package com.cachenote.server.controller;


import com.cachenote.server.payload.Reponse.NoteResponse;
import com.cachenote.server.payload.Request.NoteRequest;
import com.cachenote.server.service.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/note")
public class NoteController {
    private NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }




    @PostMapping
    @PreAuthorize("hasAnyRole('USER_NORMAL', 'USER_PAID', 'USER_ADMIN')")
    public ResponseEntity<NoteResponse> createNote(@RequestBody NoteRequest noteRequest) {
        return new ResponseEntity<NoteResponse>(noteService.createNote(noteRequest), HttpStatus.CREATED);

    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER_NORMAL', 'USER_PAID', 'USER_ADMIN')")
    public ResponseEntity<List<NoteResponse>> getAllNote() {
        return new ResponseEntity<>(noteService.getAllNotes(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER_NORMAL', 'USER_PAID', 'USER_ADMIN')")
    public ResponseEntity<NoteResponse> getNoteById(@PathVariable Long id) {
        return new ResponseEntity<>(noteService.getNoteById(id), HttpStatus.OK);
    }

    @PutMapping()
    @PreAuthorize("hasAnyRole('USER_NORMAL', 'USER_PAID', 'USER_ADMIN')")
    public ResponseEntity<Void> updateNoteById(@RequestBody NoteRequest noteRequest) {
        noteService.updateNoteById(noteRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER_NORMAL', 'USER_PAID', 'USER_ADMIN')")
    public ResponseEntity<Void> deleteNoteById(@PathVariable String id) {
        noteService.deleteNoteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
