package com.cachenote.server.controller;


import com.cachenote.server.payload.Request.NoteRequest;

import com.cachenote.server.service.NoteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.annotation.Rollback;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NoteControllerTests {
    private static final ObjectMapper mapper = new ObjectMapper();

//    @InjectMocks
//    private NoteController noteController;
//
//    @Mock
//    private NoteService noteService;

    @Autowired
    private NoteController noteController;

    @Autowired
    private NoteService noteService;

    private MockMvc mockMvc;

    String prefix = "/api/v1/note";

    @Before
    public void setup() {
        initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(noteController).build();
    }

    @Test
    @Rollback(false)
    public void shouldCreateNewUser() throws Exception {
        final NoteRequest noteRequest = new NoteRequest();
        noteRequest.setBody("testBody");

        String json = mapper.writeValueAsString(noteRequest);
        mockMvc.perform(post(prefix + "/").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isCreated());

    }




}
