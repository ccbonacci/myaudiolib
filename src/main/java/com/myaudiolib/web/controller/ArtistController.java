package com.myaudiolib.web.controller;

import com.myaudiolib.web.model.Artist;
import com.myaudiolib.web.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;


@Controller
@RequestMapping("/artists")
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;

    // Récupérer un artiste par son id
    @GetMapping(value = "/{id}")
    public String getArtistById(
            @PathVariable Long id,
            final ModelMap model) {
        Optional<Artist> artist = artistRepository.findById(id);

        model.put("artist", artist);
        return "detailArtist";
    }
}
