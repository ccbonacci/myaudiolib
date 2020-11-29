package com.myaudiolib.web.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;


@Entity
public class Artiste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String albums;

    public Artiste() {
    }

    public Artiste(Long id, String name, String albums) {
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

    public String getAlbums() {
        return albums;
    }

    public void setAlbums(String albums) {
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
