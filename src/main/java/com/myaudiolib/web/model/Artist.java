package com.myaudiolib.web.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "artist")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ArtistId")
    private Long id;

    private String name;

    @JsonIgnoreProperties("artist")
    @OneToMany(mappedBy = "artistId", fetch =  FetchType.EAGER , cascade = CascadeType.REMOVE)
    private List<Album> album;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public List<Album> getAlbum() {return album;}

    public void setAlbum(List<Album> album) {this.album = album;}
}
