package com.myaudiolib.web.repository;

import com.myaudiolib.web.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {

    Album findByTitle(String title);
}
