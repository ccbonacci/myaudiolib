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
        System.out.println(id);
        if (optionalArtiste.isEmpty()){
            //erreur 404

            throw new EntityNotFoundException("L'artiste " + id + " recherché n'éxiste pas dans la base de données");
        }
        return optionalArtiste.get();
    }


    // Search Artist
    @GetMapping(
            value = "",
            params = {"name"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Artist searchByName(@RequestParam String name)
    {
        Artist artist = artisteRepository.findByName(name);
        if (artist == null){
            throw new EntityNotFoundException("L'artiste nommé " + name + " n'a pas été trouvé");
        }
        return artist;
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
    public void deleteArtist(@PathVariable(value = "id")Long id){
        // 404
        if( ! artisteRepository.existsById(id)){
            throw new EntityNotFoundException("L'artiste " + id + " n'existe pas.");
        }
        artisteRepository.deleteById(id);
    }

}
