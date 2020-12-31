package com.myaudiolib.web.controller;

import com.myaudiolib.web.model.Album;
import com.myaudiolib.web.model.Artist;
import com.myaudiolib.web.repository.AlbumRepository;
import com.myaudiolib.web.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;




@Controller
@RequestMapping(value = "/albums")
public class AlbumController {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;

    // Ajouter un album à un artist
    @PostMapping(value = "",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addAlbum(
            @RequestBody Album album,
            final ModelMap model) {

        // gestion de l'erreur 409
        if (albumRepository.findByTitle(album.getTitle()) != null){
            // 409
            throw new EntityNotFoundException("L'album " + album.getTitle() + " existe pour cet artiste.");
        }
        model.put("album", album.getId());
        return "detailArtist";
    }


    // supprimer un album
    @GetMapping(value = "/{id}/delete")
    public RedirectView deleteAlbum(
            @PathVariable(value= "id") Long id)
    {
        Artist artist = artistRepository.getOne(id);
        albumRepository.deleteById(id);
        return new RedirectView("/artists/" +  artist);
    }


}
