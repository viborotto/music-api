package com.viborotto.musicapi.service.impl;

import com.viborotto.musicapi.exceptions.BandaNotFoundException;
import com.viborotto.musicapi.exceptions.NomeBandaDontExist;
import com.viborotto.musicapi.model.Banda;
import com.viborotto.musicapi.repository.BandaRepository;
import com.viborotto.musicapi.service.BandaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BandaServiceImpl implements BandaService {

    @Autowired
    private BandaRepository bandaRepository;

    public Banda getBandaByNome(String nome) throws NomeBandaDontExist {
        Banda encontrarBandaPorNome = bandaRepository.findBandaByNome(nome);

        if (encontrarBandaPorNome.getNome().isEmpty()){
            throw new NomeBandaDontExist("Nome de Banda procurado nao existe");
        }

        return encontrarBandaPorNome;
    }

    @Override
    public List<Banda> listarBandas() {
        //caso nao tenha banda no DB retornar um exception informando
        return bandaRepository.findAll();
    }

    @Override
    public Banda getBanda(Long id) {
        Optional<Banda> optionalBanda = bandaRepository.findById(id);
        if (optionalBanda.isEmpty()){
            throw new BandaNotFoundException("Banda nao encontrada");
        }
        return optionalBanda.get();
    }

    @Override
    public Banda criarBanda(Banda banda) {
        return bandaRepository.save(banda);
    }

    @Override
    public Banda atualizarBanda(Long id, Banda banda) {
        Optional<Banda> optionalBanda = bandaRepository.findById(id);
        optionalBanda.get().setNome(banda.getNome());
        optionalBanda.get().setAlbums(banda.getAlbums());
        return optionalBanda.get();
    }

    @Override
    public void deletarBanda(Long id) {
        Optional<Banda> optionalBanda = bandaRepository.findById(id);
        if (optionalBanda.isEmpty()){
            throw new BandaNotFoundException("Banda nao encontrada");
        }
        bandaRepository.delete(optionalBanda.get());
    }
}
