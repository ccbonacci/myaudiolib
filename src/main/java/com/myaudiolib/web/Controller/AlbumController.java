package com.myaudiolib.web.Controller;
import com.myaudiolib.web.model.Album;
import com.myaudiolib.web.model.Artist;
import com.myaudiolib.web.repository.AlbumRepository;
import com.myaudiolib.web.repository.ArtisteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;



@RestController
@CrossOrigin
@RequestMapping(value="/albums")
public class AlbumController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlbumController.class);

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtisteRepository artisteRepository;


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

    // Append an album to an artist
    @PostMapping(
            value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED) //201
    public void addAlbumToArtist(
            @RequestBody Album album // the album
    ){
        // Errors handlering
        LOGGER.info("Dans la fonction ADD Album = {}", album.toString());
        // Sauvegarder
        albumRepository.save(album);
    }


    // Delete an album
    @DeleteMapping(
            value = "/{id}"
    )
    @ResponseStatus(value = HttpStatus.NO_CONTENT) // 204
    public void deleteAlbum(
            @PathVariable(value = "id")Long id
    ){
        albumRepository.deleteById(id);
    }

}
