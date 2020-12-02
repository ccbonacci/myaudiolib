package com.myaudiolib.web.Controller;

import com.myaudiolib.web.model.Artiste;
import com.myaudiolib.web.repository.ArtisteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/artistes")
public class ArtisteController {

    @Autowired
    private ArtisteRepository artisteRepository;

    // Affiche un artiste
    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    ) public Artiste getArtiste(@PathVariable(value = "id")Long id) {
        Artiste artiste = artisteRepository.findArtisteById(id);
        return artiste;
    }

    public Page<Artiste> listArtiste(
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
