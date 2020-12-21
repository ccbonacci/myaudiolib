package com.myaudiolib.web.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "artist")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ArtistId")
    private Long id;

    @Column(name = "Name")
    private String name;

    @JsonIgnoreProperties("artist")
    @OneToMany(mappedBy = "artist", fetch =  FetchType.EAGER , cascade = CascadeType.REMOVE)
    private Set<Album> albums = new HashSet<>();

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public Set<Album> getAlbums() {return albums;}

    public void setAlbums(Set<Album> albums) {this.albums = albums;}
}
