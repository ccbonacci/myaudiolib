package com.myaudiolib.web.repository;

import com.myaudiolib.web.model.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ArtisteRepository extends JpaRepository<Artist, Long> {


    Artist findByName(String name);


    // Afficher la recherche d'une liste d'artistes avec une partie du nom;

    List<Artist> findByNameContainingIgnoreCase(String name);
    Page<Artist> findByNameContainingIgnoreCase(String name, Pageable pageable);

}
