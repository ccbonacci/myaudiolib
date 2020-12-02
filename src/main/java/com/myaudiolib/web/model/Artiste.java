package com.myaudiolib.web.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "artist")
public class Artiste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ArtistId")
    private Long id;

    @Column(name = "Name")
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "artist", fetch =  FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Album> albums;

    public Artiste() {
    }

    public Artiste(Long id, String name, List<Album> albums) {
        this.id = id;
        this.name = name;
        this.albums = albums;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artiste artiste = (Artiste) o;
        return Objects.equals(id, artiste.id) &&
                Objects.equals(name, artiste.name) &&
                Objects.equals(albums, artiste.albums);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, albums);
    }

    @Override
    public String toString() {
        return "Artiste{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", albums='" + albums + '\'' +
                '}';
    }


}
