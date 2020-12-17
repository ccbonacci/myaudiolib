package com.myaudiolib.web.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "Album")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AlbumId")
    private Long id;

    @Column(name = "Title")
    private String title;


    @ManyToOne
    @JoinColumn(name = "ArtistId")
    private Artist artist;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtistId() {
        return artist;
    }

    public void setArtistId(Artist artist) {
        this.artist = artist;
    }

}