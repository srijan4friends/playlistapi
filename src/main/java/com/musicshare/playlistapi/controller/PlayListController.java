package com.musicshare.playlistapi.controller;

import com.musicshare.playlistapi.entity.PlayList;
import com.musicshare.playlistapi.entity.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/playlist")
public class PlayListController {

    @PostMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public PlayList createPlayListWithName(@PathVariable String name) {

        return new PlayList(name, new ArrayList<Song>());
    }
}
