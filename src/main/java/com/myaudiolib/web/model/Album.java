package com.myaudiolib.web.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "album")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AlbumId")
    private Long id;

    @Column(name = "Title")
    private String title;


    @ManyToOne()
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

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
//    public long getArtistId() {
//        return artist.getId();
//    }
//    public void setArtistId(Long id) {
//        this.artist.setId(id);
//    }
}