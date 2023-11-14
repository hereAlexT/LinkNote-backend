package com.cachenote.server.controller;


import com.cachenote.server.common.GlobalExceptionHandler;
import com.cachenote.server.payload.Request.NoteRequest;

import com.cachenote.server.service.NoteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
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
import java.util.Random;

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

    @Autowired
    private List<HttpMessageConverter<?>> messageConverters;

    /**************************/

    private MockMvc mockMvc;

    final String notePrefix = "/api/v1/note";

    @Before
    public void setup() {
        initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(noteController).setControllerAdvice(GlobalExceptionHandler.class).build();

    }

    @Test
    public void shouldCreateNewUserAndGetById() throws Exception {
        String randomBody = UUID.randomUUID().toString();

        final NoteRequest noteRequest = new NoteRequest();
        noteRequest.setBody(randomBody);

        String json = mapper.writeValueAsString(noteRequest);
        MvcResult result = mockMvc.perform(post(notePrefix + "/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // check if the response is JSON
                .andExpect(jsonPath("$.id").exists()) // check if id exists
                .andExpect(jsonPath("$.body").value(randomBody)) // check if body is what we sent
                .andReturn();

        // get the note to see whether it is correctly created.
        String response = result.getResponse().getContentAsString();
        System.out.println(response);
        Long id = JsonPath.parse(response).read("$.id");

        // perform another request to getNoteById
        mockMvc.perform(get(notePrefix + "/" + id)
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
            MvcResult result = mockMvc.perform(post(notePrefix + "/")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isCreated())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // check if the response is JSON
                    .andExpect(jsonPath("$.id").exists()) // check if id exists
                    .andExpect(jsonPath("$.body").value(randomBody)) // check if body is what we sent
                    .andReturn();

            // get the note to see whether it is correctly created.
            String response = result.getResponse().getContentAsString();
            Long id = JsonPath.parse(response).read("$.id");
            createdNoteIds.add(id.toString());
            createdNoteBodies.add(randomBody);
            System.out.println("body " + randomBody + " id " + id );
        }

        // perform another request to getAllNotes
        MvcResult getAllNotesResult = mockMvc.perform(get(notePrefix + "/")
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
            System.out.println(body);
            boolean noteExists = allNotes.stream().anyMatch(note ->
                    id.equals(note.get("id").toString()) && body.equals(note.get("body"))
            );
            // If the note does not exist, print the id and body
            if (!noteExists) {
                System.out.println("No matching note found for ID: " + id + " with body: " + body);
            }
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
        MvcResult result = mockMvc.perform(post(notePrefix + "/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        Long id = JsonPath.parse(response).read("$.id");

        // Update the note
        NoteRequest updateRequest = new NoteRequest();
        updateRequest.setId(id);
        updateRequest.setBody("Updated body");

        json = mapper.writeValueAsString(updateRequest);
        mockMvc.perform(put(notePrefix + "/")
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
        MvcResult result = mockMvc.perform(post(notePrefix + "/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        Long id = JsonPath.parse(response).read("$.id");

        // Delete the note
        mockMvc.perform(delete(notePrefix + "/" + id))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldNotUpdateNonExistingNote() throws Exception {
        // Try to update a note that doesn't exist
        NoteRequest updateRequest = new NoteRequest();
        Long nonExistingId = new Random().nextLong();
        updateRequest.setId(nonExistingId);
        updateRequest.setBody("Updated body");

        String json = mapper.writeValueAsString(updateRequest);
        mockMvc.perform(put(notePrefix + "/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound());

    }


    @Test
    public void shouldNotGetNonExistingNote() throws Exception {
        // Try to get a note that doesn't exist
        String nonExistingId = "2131daADSA";

        mockMvc.perform(get(notePrefix + "/" + nonExistingId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }
}
