package com.myaudiolib.web.Controller;

import com.myaudiolib.web.model.Artist;
import com.myaudiolib.web.repository.ArtisteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import java.util.Optional;


@RestController
@RequestMapping(value = "/artists")
public class ArtistController {

    @Autowired
    private ArtisteRepository artisteRepository;

    // Show an artist
    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    ) public Artist getArtiste(@PathVariable(value = "id")Long id) {
        Optional<Artist> optionalArtiste = artisteRepository.findById(id); // L'artiste est encapsulé dans un optional non null
        if (optionalArtiste.isEmpty()){
            //erreur 404
            throw new EntityNotFoundException("L'artiste " + id + " recherché n'éxiste pas dans la base de données");
            // return List vide "" ;
        }
        return optionalArtiste.get();
    }

    // Search Artist
//    @GetMapping(
//            value = "",
//            produces = MediaType.APPLICATION_JSON_VALUE,
//            params = {"name"})
//    public List<Artist> searchArtist(
//                @RequestParam(value = "name")String name)
//    {
//        List<Artist> artist = artisteRepository.findByNameContainingIgnoreCase(name);
//        if (artist == null){
//            throw new EntityNotFoundException("L'artiste nommé " + name + " n'a pas été trouvé");
//        }
//        return artist;
//    }
     // second method Search Artist
    @GetMapping(
            value = "",
            produces = MediaType.APPLICATION_JSON_VALUE,
            params = {"name", "page", "size", "sortProperty", "sortDirection"})
    public Page<Artist> searchArtist(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "20") Integer size,
            @RequestParam(value = "sortProperty") String sortProperty,
            @RequestParam(value = "sortDirection", defaultValue = "ACS") String sortDirection
    ){
        if (artisteRepository.findByNameContainingIgnoreCase(name) == null){
            // 404
            throw new EntityNotFoundException("L'artiste nommé " + name + " n'a pas été trouvé");
        }

        return artisteRepository.findByNameContainingIgnoreCase(name,PageRequest.of(
                page,
                size,
                Sort.Direction.fromString(sortDirection),
                sortProperty));
    }

    // Show artists list by page
    @GetMapping(
            value="",
            produces = MediaType.APPLICATION_JSON_VALUE,
            params =  {"page", "size", "sortProperty", "sortDirection"}
    )
    public Page<Artist> listArtiste(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "20") Integer size,
            @RequestParam(value = "sortProperty", defaultValue = "name")String sortProperty,
            @RequestParam(value = "sortDirection", defaultValue = "ACS") String sortDirection
            ){

        if (page < 0) {
            // 400
            throw new IllegalArgumentException("Le numéro de la page doit être positif ou égal à zéro!");
        }
        if(!"ASC".equalsIgnoreCase(sortDirection) && !"DESC".equalsIgnoreCase(sortDirection)){
            throw new IllegalArgumentException("Le paramètre sortDirection doit valoir ASC ou DESC");
        }

        return artisteRepository.findAll(PageRequest.of(
                page,
                size,
                Sort.Direction.fromString(sortDirection),
                sortProperty));
    }

    // Create an artist
    @PostMapping(
            value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.CREATED) // 201
    public Artist createArtist(@RequestBody Artist artist){
        if (artisteRepository.findByName(artist.getName()) != null){
            // 409
            throw new EntityExistsException("Il y existe déjà un artiste nommé " + artist.getName() + " dans la base de données");
        }
        return artisteRepository.save(artist);
    }

    // Update an artist
    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.CREATED) // 201
    public Artist updateArtist(
            @PathVariable Long id,
            @RequestBody Artist artist)
    {
        // 404
        if (!artisteRepository.existsById(id)){
            throw new EntityNotFoundException("L'artiste dont l'identifiant " + id + " n'a pas été trouvé.");
        }
        return artisteRepository.save(artist);
    }

    // Delete an artist
    @DeleteMapping(
            value = "/{id}"
    )
    @ResponseStatus(value = HttpStatus.NO_CONTENT) // 204
    public RedirectView deleteArtist(@PathVariable(value = "id")Long id){
        // 404
        if( ! artisteRepository.existsById(id)){
            throw new EntityNotFoundException("L'artiste " + id + " n'a pas été trouvé.");
        }
        artisteRepository.deleteById(id);
        return new RedirectView("/artits?page=0&size=10&sortProperty=name&sortDirection=DESC");
    }

}
