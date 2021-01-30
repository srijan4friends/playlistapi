package com.musicshare.playlistapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/playlist")
public class PlayListController {



    @PostMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPlayListWithName(@PathVariable String name){


    }
}
