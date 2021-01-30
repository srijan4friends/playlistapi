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

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@SpringBootTest
@Transactional
public class PlayListControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    /**
     * When a playlist is created with a name
     * Then a confirmation is returned that it was successful.
     * And the playlist is empty.
     *
     * @throws Exception
     */

    @Test
    public void test_createPlayListWithName() throws Exception {

        mockMvc.perform(post("/api/v1/playlist")
                .param("name", "playlist1"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("playlist1"))
                .andExpect(jsonPath("$.songs", hasSize(0)));

    }

    /**
     * When a playlist is created without a name
     * Then a message is returned that a name is required.
     *
     * @throws Exception
     */

    @Test
    public void test_createPlayListWithoutName() throws Exception {

        mockMvc.perform(post("/api/v1/playlist")
                .param("name", ""))
                .andExpect(status().isBadRequest())
                .andExpect(status().reason("playlist name is mandatory"));
    }

    /**
     * Add the song to the existing playlist.
     *
     * @throws Exception
     */
    @Test
    public void test_addSongsToPlaylist() throws Exception {
        mockMvc.perform(post("/api/v1/playlist")
                .param("name", "playlist1"))
                .andExpect(status().isCreated());

        Song song = Song.builder()
                .songName("song1").build();

        mockMvc.perform(post("/api/v1/playlist/song")
                .param("name", "playlist1")
                .content(objectMapper.writeValueAsString(song)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("playlist1"))
                .andExpect(jsonPath("$.songs", hasSize(1)));

    }

    /**
     * Given a song doesn't exist
     * And a playlist with 1 song
     * When the song is added to the playlist
     * Then the playlist still has 1 song
     * And a message is returned that the song doesn't exist.
     */

    @Test
    public void test_addNonExistentSongToPlayList() throws Exception {


        mockMvc.perform(post("/api/v1/playlist")
                .param("name", "playlist1"))
                .andExpect(status().isCreated());

        Song song1 = Song.builder()
                .songName("song1").build();

        mockMvc.perform(post("/api/v1/playlist/song")
                .param("name", "playlist1")
                .content(objectMapper.writeValueAsString(song1)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.name").value("playlist1"))
                .andExpect(jsonPath("$.songs", hasSize(1)));

        Song song = Song.builder()
                .songName("noSong").build();
        mockMvc.perform(post("/api/v1/playlist/song")
                .param("name", "playlist1")
                .content(objectMapper.writeValueAsString(song))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print())
                .andExpect(status().reason("song doesn't exist"));
    }


}
