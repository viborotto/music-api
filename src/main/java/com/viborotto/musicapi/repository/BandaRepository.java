package com.viborotto.musicapi.repository;

import com.viborotto.musicapi.model.Banda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BandaRepository extends JpaRepository<Banda, Long> {

    Banda findBandaByNome(String name);
}
