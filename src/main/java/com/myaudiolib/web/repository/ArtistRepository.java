package com.myaudiolib.web.repository;

import com.myaudiolib.web.model.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    Artist findByName(String name);

    List<Artist> findAllByNameContaining(String name);
    Page<Artist> findByNameContaining(String name, Pageable pageable);
}
