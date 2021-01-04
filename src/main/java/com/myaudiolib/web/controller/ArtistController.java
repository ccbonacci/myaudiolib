package com.myaudiolib.web.controller;

import com.myaudiolib.web.model.Artist;
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
import java.util.List;
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
            throw new EntityNotFoundException("L'artiste dont l'identifiant est " + id + "  n'a pas été trouvé.");
        }
        model.put("artist", artistOptional.get());
        return "detailArtist";
    }


    // Récupérer un artiste par nom
    @GetMapping(value = "",
            params = {"name"})
    public String searchArtistByName(
            @RequestParam(value = "name") String name,
            final ModelMap model){
        List<Artist> artistList = artistRepository.findAllByNameContaining(name);
        // gestion de l'erreur 404
        if (artistList == null) {
            throw new EntityNotFoundException("L'artiste " + name + " recherché n'a pas été trouvé.");
        }
        model.put("artists", artistList);
        return "listeArtists";
    }


    // Afficher la liste des artistes
    @GetMapping(value = "",
            produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String listArtist(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "20") Integer size,
            @RequestParam(value = "sortProperty") String sortProperty,
            @RequestParam(value = "sortDirection", defaultValue = "ASC") String sortDirection,
            final ModelMap model){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.fromString(sortDirection), sortProperty);
        Page<Artist> pageArtist = artistRepository.findAll(pageRequest);
        model.put("artists", pageArtist);
        model.put("pageNumber", page + 1);
        model.put("previousPage", page - 1);
        model.put("nextPage", page + 1);
        model.put("start", page * size + 1);
        model.put("end", (page) * size + pageArtist.getNumberOfElements());

        return "listeArtists";

    }


    // Créer un artiste

    //  Affiche une page détail vide
//    @GetMapping(value = "/new/")
//    public String newArtist(
//            @PathVariable Artist artist,
//            final ModelMap model){
//        model.put("artist", new Artist());
//        return "detailArtist";
//    }
    // Sauvegarder un artiste
@PostMapping(
        value = "/addArtist/",
        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
public String createArtist(
        @RequestBody Artist artist,
        final ModelMap model)
{
    model.put("artist", artistRepository.save(artist));
    return "detailArtist";
}



    // Supprimer un artiste
    @GetMapping(value = "/{id}/delete")
    public RedirectView deleteArtist(
            @PathVariable Long id)
    {
        if (! artistRepository.existsById(id)){

        }
        artistRepository.deleteById(id);
        return new RedirectView("/artists?page=0&size=10&sortProperty=name&sortDirection=ASC");
    }


}
