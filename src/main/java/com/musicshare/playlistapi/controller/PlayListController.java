package com.musicshare.playlistapi.controller;

import com.musicshare.playlistapi.entity.PlayList;
import com.musicshare.playlistapi.entity.Song;
import com.musicshare.playlistapi.service.PlayListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/playlist")
public class PlayListController {

    @Autowired
    PlayListService playListService;

    @PostMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public PlayList createPlayListWithName(@PathVariable String name) {

        return playListService.createPlayListWithName(name);
    }

    @PostMapping("/song")
    public PlayList addSongsToPlayList(@RequestParam String playlistname, @RequestBody Song song) {
        return playListService.addSongsToPlayList(playlistname,song);
    }
}
