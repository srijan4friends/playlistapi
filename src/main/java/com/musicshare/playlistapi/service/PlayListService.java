package com.musicshare.playlistapi.service;

import com.musicshare.playlistapi.entity.PlayList;
import com.musicshare.playlistapi.entity.Song;
import com.musicshare.playlistapi.repository.PlayListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PlayListService {

    @Autowired
    PlayListRepository playListRepository;

    public PlayList createPlayListWithName(String name) {

        PlayList playList = PlayList.builder()
                .name(name)
                .songs(new ArrayList<Song>())
                .build();


        return playListRepository.save(playList);

    }
}
