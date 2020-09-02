package com.viborotto.musicapi.repository;

import com.viborotto.musicapi.model.Album;
import com.viborotto.musicapi.model.Banda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    Album getAlbumByIdBanda(Long bandaId);

    List<Album> findByBanda(Banda banda);

    Album findByIdAndBanda(Long id, Banda banda);
}
