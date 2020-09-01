package com.viborotto.musicapi.service;


import com.viborotto.musicapi.model.Banda;

import java.util.List;

public interface BandaService {

    List<Banda> listarBandas();

    Banda getBanda(Long id);

    Banda criarBanda(Banda banda);

    Banda atualizarBanda(Long id, Banda banda);

    void deletarBanda(Long id);

}
