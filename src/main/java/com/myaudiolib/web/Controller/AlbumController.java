package com.myaudiolib.web.Controller;
import com.myaudiolib.web.model.Album;
import com.myaudiolib.web.model.Artist;
import com.myaudiolib.web.repository.AlbumRepository;
import com.myaudiolib.web.repository.ArtisteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

public class AlbumController {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtisteRepository artisteRepository;

    // Show an album
    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    ) public Album getAlbum(@PathVariable(value = "id")Long id) {
        // Encapsulation de l'album dans un optional
        Optional<Album> optionalAlbum = albumRepository.findById(id);
        if (optionalAlbum.isEmpty()){
            //erreur 404
        }
        return optionalAlbum.get();
    }

    // Append an album to an artist
    @GetMapping(
            value = "/{id}/{album}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addAlbumToArtist(
            @PathVariable Long id, // artist
            @PathVariable Album album // album
    ){
        // Récupérer l'album à partir de l'objet album
        Optional<Album> optionalAlbum = albumRepository.findById(id);
        Album album1 = optionalAlbum.get();

        // Gestion des erreurs

        // Récupérer l'artiste à partir de son id
        Optional<Artist> optionalArtist = artisteRepository.findById(id);
        Artist artist = optionalArtist.get();

        // Setter l'album à l'artiste et sauvegarder
        album1.setArtistId(artist);
        albumRepository.save(album1);

    }


    // Delete an album

}
