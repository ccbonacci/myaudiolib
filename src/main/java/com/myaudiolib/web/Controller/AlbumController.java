package com.myaudiolib.web.Controller;
import com.myaudiolib.web.model.Album;
import com.myaudiolib.web.repository.AlbumRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.EntityExistsException;
import java.util.Set;


@RestController
@RequestMapping(value="/albums")
public class AlbumController {

    @Autowired
    private AlbumRepository albumRepository;

//    @Autowired
//    private ArtisteRepository artisteRepository;
    // Show an album
//    @GetMapping(
//            value = "/{id}",
//            produces = MediaType.APPLICATION_JSON_VALUE
//    ) public Album getAlbum(@PathVariable(value = "id")Long id) {
//        // Encapsulation de l'album dans un optional
//        Optional<Album> optionalAlbum = albumRepository.findById(id);
//        if (optionalAlbum.isEmpty()){
//            //erreur 404
//        }
//        return optionalAlbum.get();
//    }

    // Add an album to an artist
    @PostMapping(
            value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.CREATED) // 201
    public Album addAlbum(
            @RequestBody Album album){


        if (albumRepository.findByTitle(album.getTitle()) != null){
            // 409
            throw new EntityExistsException("L'album " + album.getTitle() + " existe pour cet artiste.");
        }
        // Sauvegarder l'album
        return albumRepository.save(album);
    }


    // Delete an album
    @DeleteMapping(
            value = "/{id}"
    )
    @ResponseStatus(value = HttpStatus.NO_CONTENT) // 204
    public RedirectView deleteAlbum(
            @PathVariable(value = "id")Long id
    ){
        albumRepository.deleteById(id);
        return new RedirectView("/artist?page=0&size=10&sortProperty=name&sortDirection=DESC");
    }

}
