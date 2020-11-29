package com.myaudiolib.web.repository;

import com.myaudiolib.web.model.Artiste;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;



public interface ArtisteRepository extends JpaRepository<Artiste, Long> {

    Artiste findByName(String name);

    Artiste findArtisteByAlbumsContainsAndIgnoreCase(String title);

    List<Artiste> findArtisteById(Long id, Pageable pageable);

    Page<Artiste> findArtisteByNameAndIdAndAlbums(String name, Long id, String title);

    @Query("select a from Artiste a where lower(a.name) = lower(:Name)")
    List<Artiste> findByNameAllIgnoreCase(@Param("Name")String Name);



}
