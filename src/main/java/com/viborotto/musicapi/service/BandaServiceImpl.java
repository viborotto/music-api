package com.viborotto.musicapi.service;

import com.viborotto.musicapi.exceptions.BandaNotFoundException;
import com.viborotto.musicapi.exceptions.NomeBandaDontExist;
import com.viborotto.musicapi.model.Banda;
import com.viborotto.musicapi.repository.BandaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BandaServiceImpl {

    @Autowired
    private BandaRepository bandaRepository;

    //TODO DELETE

    public Iterable<Banda> listarBandas(){
        return bandaRepository.findAll();
    }

    public Banda porId(Long id) throws Exception {
        Optional<Banda> optionalBanda = bandaRepository.findById(id);

        if (optionalBanda.isEmpty()){
            throw new BandaNotFoundException("Banda nao encontrada");
        }

        return optionalBanda.get();
    }

    public Banda porNome(String nome) throws NomeBandaDontExist {
        Banda encontrarBandaPorNome = bandaRepository.findBandaByNome(nome);

        if (encontrarBandaPorNome.getNome().isEmpty()){
            throw new NomeBandaDontExist("Nome de Banda procurado nao existe");
        }

        return encontrarBandaPorNome;
    }

    public Banda criarBanda(Banda banda) {
        return bandaRepository.save(banda);
    }
}
