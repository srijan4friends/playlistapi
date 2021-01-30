package com.musicshare.playlistapi.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class PlayList {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Song> songs;

}
