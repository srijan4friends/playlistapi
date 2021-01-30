package com.musicshare.playlistapi.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Song {
    @Id
    @GeneratedValue
    private String id;

}
