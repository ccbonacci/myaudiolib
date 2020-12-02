package com.myaudiolib.web.repository;

import com.myaudiolib.web.model.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long> {

    Album findByTitle(String title);

    List<Album> findByTitleIgnoreCase(String title);

    Page<Album> findByTitleIgnoreCase(String title, Pageable pageable);

    Page<Album> findAlbumById(Long id, Pageable pageable);

    List<Album> findAlbumByArtist(String Name);

    @Query("select al from Album al where lower(al.title) = lower(:TitleOrArtist) or lower(al.artist) = lower(:TitleOrArtist)")
    List<Album> findAlbumByTitleOrArtistIgnoreCase(@Param("TitleOrArtist")String TitleOrArtist);


}
