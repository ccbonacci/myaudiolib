package com.myaudiolib.web.repository;

import com.myaudiolib.web.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ArtisteRepository extends JpaRepository<Artist, Long> {


    Artist findByName(String name);

}
