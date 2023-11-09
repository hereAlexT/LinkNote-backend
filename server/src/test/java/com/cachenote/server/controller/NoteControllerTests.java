package com.cachenote.server.controller;


import com.cachenote.server.payload.Request.NoteRequest;

import com.cachenote.server.service.NoteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NoteControllerTests {
    private static final ObjectMapper mapper = new ObjectMapper();
    /*Below code won't insert anything into db, it works even the database is closed.*/
//    @InjectMocks
//    private NoteController noteController;
//
//    @Mock
//    private NoteService noteService;


    /* Insert to DB and don't rollback, db must be opened*/
    @Autowired
    private NoteController noteController;

    @Autowired
    private NoteService noteService;

    /**************************/

    private MockMvc mockMvc;

    String prefix = "/api/v1/note";

    @Before
    public void setup() {
        initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(noteController).build();
    }

    @Test
    public void shouldCreateNewUserAndGetById() throws Exception {
        String randomBody = UUID.randomUUID().toString();

        final NoteRequest noteRequest = new NoteRequest();
        noteRequest.setBody(randomBody);

        String json = mapper.writeValueAsString(noteRequest);
        MvcResult result = mockMvc.perform(post(prefix + "/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // check if the response is JSON
                .andExpect(jsonPath("$.id").exists()) // check if id exists
                .andExpect(jsonPath("$.body").value(randomBody)) // check if body is what we sent
                .andReturn();

        // get the note to see whether it is correctly created.
        String response = result.getResponse().getContentAsString();
        String id = JsonPath.parse(response).read("$.id");

        // perform another request to getNoteById
        mockMvc.perform(get(prefix + "/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // check if the response is JSON
                .andExpect(jsonPath("$.id").value(id)) // check if id is correct
                .andExpect(jsonPath("$.body").value(randomBody)); // check if body is correct
    }


    @Test
    public void shouldCreateNotesAndGetAllNotes() throws Exception {
        List<String> createdNoteIds = new ArrayList<>();
        List<String> createdNoteBodies = new ArrayList<>();

        // Create 10 notes
        for (int i = 0; i < 10; i++) {
            String randomBody = UUID.randomUUID().toString();

            final NoteRequest noteRequest = new NoteRequest();
            noteRequest.setBody(randomBody);

            String json = mapper.writeValueAsString(noteRequest);
            MvcResult result = mockMvc.perform(post(prefix + "/")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isCreated())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // check if the response is JSON
                    .andExpect(jsonPath("$.id").exists()) // check if id exists
                    .andExpect(jsonPath("$.body").value(randomBody)) // check if body is what we sent
                    .andReturn();

            // get the note to see whether it is correctly created.
            String response = result.getResponse().getContentAsString();
            String id = JsonPath.parse(response).read("$.id");

            createdNoteIds.add(id);
            createdNoteBodies.add(randomBody);
        }

        // perform another request to getAllNotes
        MvcResult getAllNotesResult = mockMvc.perform(get(prefix + "/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // check if the response is JSON
                .andReturn();

        String getAllNotesResponse = getAllNotesResult.getResponse().getContentAsString();
        List<Map<String, Object>> allNotes = JsonPath.parse(getAllNotesResponse).read("$");

        // check if the created notes exist in the returned list
        for (int i = 0; i < createdNoteIds.size(); i++) {
            String id = createdNoteIds.get(i);
            String body = createdNoteBodies.get(i);

            boolean noteExists = allNotes.stream().anyMatch(note ->
                    id.equals(note.get("id")) && body.equals(note.get("body"))
            );

            assertTrue("Note with id " + id + " and body " + body + " should exist in the returned list", noteExists);
        }
    }

    @Test
    public void shouldUpdateExistingNote() throws Exception {
        String randomBody = UUID.randomUUID().toString();

        // Create a note
        NoteRequest noteRequest = new NoteRequest();
        noteRequest.setBody(randomBody);

        String json = mapper.writeValueAsString(noteRequest);
        MvcResult result = mockMvc.perform(post(prefix + "/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        String id = JsonPath.parse(response).read("$.id");

        // Update the note
        NoteRequest updateRequest = new NoteRequest();
        updateRequest.setId(id);
        updateRequest.setBody("Updated body");

        json = mapper.writeValueAsString(updateRequest);
        mockMvc.perform(put(prefix + "/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteExistingNote() throws Exception {
        String randomBody = UUID.randomUUID().toString();

        // Create a note
        NoteRequest noteRequest = new NoteRequest();
        noteRequest.setBody(randomBody);

        String json = mapper.writeValueAsString(noteRequest);
        MvcResult result = mockMvc.perform(post(prefix + "/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        String id = JsonPath.parse(response).read("$.id");

        // Delete the note
        mockMvc.perform(delete(prefix + "/" + id))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldNotUpdateNonExistingNote() throws Exception {
        // Try to update a note that doesn't exist
        NoteRequest updateRequest = new NoteRequest();
        updateRequest.setId("non-existing-id");
        updateRequest.setBody("Updated body");

        String json = mapper.writeValueAsString(updateRequest);
        mockMvc.perform(put(prefix + "/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound()); // Assuming your service returns 404 for non-existing notes
    }

    @Test
    public void shouldNotDeleteNonExistingNote() throws Exception {
        // Try to delete a note that doesn't exist
        mockMvc.perform(delete(prefix + "/non-existing-id"))
                .andExpect(status().isNotFound()); // Assuming your service returns 404 for non-existing notes
    }






}
