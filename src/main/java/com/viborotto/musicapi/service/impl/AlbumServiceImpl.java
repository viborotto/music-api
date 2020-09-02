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

    public AlbumServiceImpl(AlbumRepository albumRepository, BandaRepository bandaRepository) {
        this.albumRepository=albumRepository;
        this.bandaRepository=bandaRepository;
    }

    @Override
    public List<Album> listarByBanda(Long idBanda) {
        Banda bandaEncontrada = validateBanda(bandaRepository.findById(idBanda));
        return albumRepository.findByBanda(bandaEncontrada);
    }

    //TODO encontrar o album porId associado a certa banda de idBanda, como seria isso?
    @Override
    public Album getByIdAndBanda(Long idAlbum, Banda banda) {
        return validateAlbum(albumRepository.findByIdAndBanda(idAlbum, banda));
    }

    @Override
    public Album inserirNovoAlbumNaBanda(Long idBanda, Album album) {
        Banda bandaEncontrada = validateBanda(bandaRepository.findById(idBanda));
        album.setBanda(bandaEncontrada);
        return albumRepository.save(album);
    }

    //TODO Como seria para Atualizar varios Albums e nao somente 1?
    @Override
    public Album atualizarAlbumdaBanda(Long idAlbum, Album album) {
        //encontro o album a ser atualizado
        Album albumDB = validateAlbum(albumRepository.findById(idAlbum));
        //encontrar a banda do respectivo album a ser atualizado
        validateBanda(bandaRepository.findById(album.getBanda().getId()));
        //atualizar campos
        albumDB.setTitulo(album.getTitulo());
        albumDB.setAno(album.getAno());
        albumDB.setMusicas(album.getMusicas());
        return albumRepository.save(albumDB);
    }

    @Override
    public void deletarAlbumDaBanda(Long idAlbum) {
        Album album = validateAlbum(albumRepository.findById(idAlbum));
        //encontrar a banda do respectivo album a ser deletado
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
