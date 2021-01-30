package com.musicshare.playlistapi.repository;

import com.musicshare.playlistapi.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    Song findBySongName(String songName);
}
