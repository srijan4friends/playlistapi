package com.musicshare.playlistapi.controller;

import com.musicshare.playlistapi.entity.PlayList;
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

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public PlayList createPlayListWithName(@RequestParam String name) throws NameRequiredException {
        if (null == name || name.isEmpty()) {
            throw new NameRequiredException();
        }
        return playListService.createPlayListWithName(name);
    }
}
