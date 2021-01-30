package com.musicshare.playlistapi.controller;

import com.musicshare.playlistapi.entity.PlayList;
import com.musicshare.playlistapi.entity.Song;
import com.musicshare.playlistapi.exception.IsNotFoundException;
import com.musicshare.playlistapi.exception.NameRequiredException;
import com.musicshare.playlistapi.service.PlayListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/playlist")
public class PlayListController {

    @Autowired
    PlayListService playListService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public PlayList createPlayListWithName(@RequestParam String name) throws NameRequiredException {
        if (null == name || name.trim().isEmpty()) {
            throw new NameRequiredException();
        }
        return playListService.createPlayListWithName(name);
    }

    @PostMapping("/song")
    public PlayList addSongsToPlayList(@RequestParam String name, @RequestBody Song song) throws IsNotFoundException {
        return playListService.addSongsToPlayList(name, song);
    }

    @DeleteMapping("{playlistName}/song")
    public PlayList deleteSongFromPlayList(@PathVariable String playlistName, @RequestBody Song song) {
        return playListService.deleteSongFromPlayList(playlistName, song);

    }
}
