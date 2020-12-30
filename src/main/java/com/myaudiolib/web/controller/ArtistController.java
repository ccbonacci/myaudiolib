package com.myaudiolib.web.controller;

import com.myaudiolib.web.model.Artist;
import com.myaudiolib.web.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;


@Controller
@RequestMapping("/artists")
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;

    // Récupérer un artiste par son id
    @GetMapping(value = "/{id}")
    public String detailArtist(
            @PathVariable Long id,
            final ModelMap model) {
        Optional<Artist> artistOptional = artistRepository.findById(id);

        // gestion de l'erreur 404
        if ((artistOptional == null) || (artistOptional.isEmpty())){
            throw new EntityNotFoundException("L'artiste dont l'identifiant est " + id + " recherché n'a pas été trouvé.");
        }
        model.put("artist", artistOptional.get());
        return "detailArtist";
    }

    // Récupérer un artiste par son nom
    @GetMapping(value = "",
            params = "nom")
    public String searchArtistByName(
            @RequestParam String name,
            final ModelMap model){
        Artist artist =  artistRepository.findByName(name);

        // gestion de l'erreur 404
        if (artist != null){
            throw new EntityNotFoundException("L'artiste " + name + " recherché n'a pas été trouvé.");
        }

        model.put("artist", artist);
        return "detailArtist";

    }


}
