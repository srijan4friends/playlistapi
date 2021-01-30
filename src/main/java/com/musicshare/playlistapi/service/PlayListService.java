package com.musicshare.playlistapi.service;

import com.musicshare.playlistapi.entity.PlayList;
import com.musicshare.playlistapi.entity.Song;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Stack;

@Service
public class PlayListService {


    public PlayList createPlayListWithName(String name) {
        PlayList playList = new PlayList(name, new ArrayList<Song>());

        return playList;
    }
}
