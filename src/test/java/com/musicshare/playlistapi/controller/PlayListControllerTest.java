package com.musicshare.playlistapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musicshare.playlistapi.entity.Song;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@SpringBootTest
public class PlayListControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void test_createPlayListWithName() throws Exception {

        mockMvc.perform(post("/api/v1/playlist/{name}", "playlist1"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("playlist1"))
                .andExpect(jsonPath("$.songs", hasSize(0)));

    }

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Add the song to the existing playlist.
     * @throws Exception
     */
    @Test
    public void test_addSongsToPlaylist() throws Exception {
        test_createPlayListWithName();
        mockMvc.perform(post("/api/v1/playlist/song")
                .param("playlistname","playlist1")
                .content(objectMapper.writeValueAsString(new Song(0,"Song1"))).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.name").value("playlist1"))
                .andExpect(jsonPath("$.songs", hasSize(1)));

    }
}
