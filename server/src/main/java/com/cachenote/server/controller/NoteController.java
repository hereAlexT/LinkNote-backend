package com.cachenote.server.controller;


import com.cachenote.server.payload.reponse.CreateNoteResponse;
import com.cachenote.server.payload.reponse.NoteResponse;
import com.cachenote.server.payload.request.UpdateNoteRequest;
import com.cachenote.server.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/note")
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }


    /**
     * It's important to make sure that submitted text already escaped
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('USER_NORMAL', 'USER_PAID', 'USER_ADMIN')")
    public ResponseEntity<CreateNoteResponse> createNote(@RequestBody @Valid UpdateNoteRequest updateNoteRequest) {
        return new ResponseEntity<>(noteService.createNote(updateNoteRequest), HttpStatus.CREATED);

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
    public ResponseEntity<String> updateNoteById(@RequestBody UpdateNoteRequest updateNoteRequest) {
        noteService.updateNoteById(updateNoteRequest);
        return ResponseEntity.ok("success");
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER_NORMAL', 'USER_PAID', 'USER_ADMIN')")
    public ResponseEntity<Void> deleteNoteById(@PathVariable String id) {
        noteService.deleteNoteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
