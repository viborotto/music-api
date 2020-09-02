package com.viborotto.musicapi.service.impl;

import com.viborotto.musicapi.exceptions.BandaNotFoundException;
import com.viborotto.musicapi.model.Album;
import com.viborotto.musicapi.model.Banda;
import com.viborotto.musicapi.repository.AlbumRepository;
import com.viborotto.musicapi.repository.BandaRepository;
import com.viborotto.musicapi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private BandaRepository bandaRepository;

    @Override
    public List<Album> listarAlbumsdaBanda(Long id) {
        Banda bandaEncontrada = validateBanda(bandaRepository.findById(id));
        return albumRepository.findByBanda(bandaEncontrada);
    }

    //TODO encontrar o album porId associado a certa banda de idBanda, como seria isso?
    @Override
    public Album getByIdAndBanda(Long idAlbum, Banda banda) {
        return albumRepository.findByIdAndBanda(idAlbum, banda);
    }

    @Override
    public Album inserirNovoAlbumNaBanda(Long idBanda, Album album) {
        Banda bandaEncontrada = validateBanda(bandaRepository.findById(idBanda));
        album.setBanda(bandaEncontrada);
        return albumRepository.save(album);
    }

    //TODO Atualizar varios Albums
    @Override
    public Album atualizarAlbumsdaBanda(Long idAlbum, Album album) {
        Album albumDb = validateAlbum(albumRepository.findById(idAlbum));
        validateBanda(bandaRepository.findById(album.getBanda().getId()));
        albumDb.setTitulo(album.getTitulo());
        albumDb.setAno(album.getAno());
        albumDb.setMusicas(album.getMusicas());
        return albumRepository.save(albumDb);
    }

    @Override
    public void deletarAlbumDaBanda(Long idAlbum) {
        Album album = validateAlbum(albumRepository.findById(idAlbum));
        validateBanda(bandaRepository.findById(album.getBanda().getId()));
        albumRepository.delete(album);
    }

    public Banda validateBanda(Optional<Banda> banda){
        if (banda.isEmpty()){
            throw new BandaNotFoundException("Banda nao existe");
        }
        return banda.get();
    }

    public Album validateAlbum(Optional<Album> album){
        if (album.isEmpty()){
            throw new BandaNotFoundException("Album nao existe");
        }
        return album.get();
    }
}
