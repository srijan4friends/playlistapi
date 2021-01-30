package com.musicshare.playlistapi.repository;

import com.musicshare.playlistapi.entity.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayListRepository extends JpaRepository<PlayList, Long> {

    PlayList findByName(String name);
}
