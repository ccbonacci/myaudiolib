package com.myaudiolib.web.Controller;

import com.myaudiolib.web.model.Artist;
import com.myaudiolib.web.repository.ArtisteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
        Optional<Artist> optionalArtiste = artisteRepository.findById(id); // L'artiste est encapsulé dans un optional
        if (optionalArtiste.isEmpty()){
            //erreur 404
        }
        return optionalArtiste.get();
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

}
