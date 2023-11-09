package com.cachenote.server.controller;


import com.cachenote.server.common.exception.NoteNotFoundException;
import com.cachenote.server.payload.Reponse.NoteResponse;
import com.cachenote.server.payload.Request.NoteRequest;
import com.cachenote.server.service.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/note")
public class NoteController {
    private NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("health")
    public ResponseEntity<String> health() {
        return new ResponseEntity<>("Health: True", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<NoteResponse> createNote(@RequestBody NoteRequest noteRequest) {
        return new ResponseEntity<>(noteService.createNote(noteRequest), HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<NoteResponse>> getAllNote() {
        return new ResponseEntity<>(noteService.getAllNotes(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<NoteResponse> getNoteById(@PathVariable String id) {
        return new ResponseEntity<>(noteService.getNoteById(id), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<Void> updateNoteById(@RequestBody NoteRequest noteRequest) {
        noteService.updateNoteById(noteRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNoteById(@PathVariable String id) {
        noteService.deleteNoteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(NoteNotFoundException.class)
    public ResponseEntity<String> handleNoteNotFoundException(NoteNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }


}
