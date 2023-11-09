package com.cachenote.server.controller;


import com.cachenote.server.payload.Request.NoteRequest;
import com.cachenote.server.service.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/note")
public class NoteController {
    private NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("health")
    public ResponseEntity<String> health() {
        return new ResponseEntity<>("Health: True", HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<NoteRequest> createNote(@RequestBody NoteRequest noteRequest) {
        return new ResponseEntity<>(noteService.createNote(noteRequest), HttpStatus.CREATED);

    }

    //todo: GET /api/v1/note 200(OK) get all note
//    @GetMapping
//    public ResponseEntity<List<NoteDto>> getAllNote(){
//        return new ResponseEntity<>(noteService.getAllNotes())
//    }

    //todo: GET /api/v1/note/{id} 200(OK) get note by Id

    //todo: PUT /api/v1/note 200(OK) update existing note with id

    //todo: DELETE /api/v1/note/{id} 200(OK) delete post by id
}
