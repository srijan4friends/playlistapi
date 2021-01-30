package com.musicshare.playlistapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@SpringBootTest
public class PlayListControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void test_createPlayListWithName() throws Exception {

        mockMvc.perform(post("/api/v1/playlist")
                .param("name", "playlist1"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("playlist1"))
                .andExpect(jsonPath("$.songs", hasSize(0)));

    }

    @Test
    public void test_createPlayListWithoutName() throws Exception {

        mockMvc.perform(post("/api/v1/playlist")
                .param("name", ""))
                .andExpect(status().isBadRequest())
                .andExpect(status().reason("playlist name is mandatory"));
    }
}
