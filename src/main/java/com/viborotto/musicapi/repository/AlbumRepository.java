package com.viborotto.musicapi.repository;

import com.viborotto.musicapi.model.Album;
import com.viborotto.musicapi.model.Banda;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Long> {

    List<Album> findByBanda(Banda banda);

    Optional<Album> findByIdAndBanda(Long id, Banda banda);
}
