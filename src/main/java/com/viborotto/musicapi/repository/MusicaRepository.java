package com.viborotto.musicapi.repository;

import com.viborotto.musicapi.model.Album;
import com.viborotto.musicapi.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MusicaRepository extends JpaRepository<Musica, Long> {

    List<Musica> findByAlbum(Album album);

    Optional<Musica> findByIdAndAlbum(Long id, Album album);
}
