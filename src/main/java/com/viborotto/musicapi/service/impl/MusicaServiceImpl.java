package com.viborotto.musicapi.service.impl;

import com.viborotto.musicapi.exceptions.BandaNotFoundException;
import com.viborotto.musicapi.model.Album;
import com.viborotto.musicapi.model.Banda;
import com.viborotto.musicapi.model.Musica;
import com.viborotto.musicapi.repository.AlbumRepository;
import com.viborotto.musicapi.repository.BandaRepository;
import com.viborotto.musicapi.repository.MusicaRepository;
import com.viborotto.musicapi.service.MusicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MusicaServiceImpl implements MusicaService {

    //precisa o usar o banda Repository?
    @Autowired
    private MusicaRepository musicaRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private BandaRepository bandaRepository;

    @Override
    public List<Musica> findByAlbum(Long idAlbum) {
        Album album = validateAlbum(albumRepository.findById(idAlbum));
        //validar a banda associada ao album[precisa?]
        validateBanda(bandaRepository.findById(album.getBanda().getId()));
        return musicaRepository.findByAlbum(album);
    }

    @Override
    public Musica getByIdAndAlbum(Long idMusica, Album album) {
        return validateMusica(musicaRepository.findByIdAndAlbum(idMusica, album));
    }

    @Override
    public Musica inserirNovaMusicaNoAlbum(Long idAlbum, Musica musica) {
        Album album = validateAlbum(albumRepository.findById(idAlbum));
        musica.setAlbum(album);
        return musicaRepository.save(musica);
    }

    @Override
    public Musica atualizarMusicaDoAlbum(Long idMusica, Musica musica) {
        Musica musicaDb = validateMusica(musicaRepository.findById(idMusica));
        //encontrar o album da respectiva musica a ser atualizada
        validateAlbum(albumRepository.findById(musica.getAlbum().getId()));
        musicaDb.setNome(musica.getNome());
        musicaDb.setDuracao(musica.getDuracao());
        return musicaRepository.save(musicaDb);
    }

    @Override
    public void deletarMusicaDoAlbum(Long idMusica) {
        //validacoes das associacoes/relacionamentos
        Musica musica = validateMusica(musicaRepository.findById(idMusica));
        Album album = validateAlbum(albumRepository.findById(musica.getAlbum().getId()));
        validateBanda(bandaRepository.findById(album.getBanda().getId()));
        musicaRepository.delete(musica);
    }

    public Album validateAlbum(Optional<Album> album){
        if (album.isEmpty()){
            throw new BandaNotFoundException("Album nao existe");
        }
        return album.get();
    }

    public Banda validateBanda(Optional<Banda> banda){
        if (banda.isEmpty()){
            throw new BandaNotFoundException("Banda nao existe");
        }
        return banda.get();
    }

    public Musica validateMusica(Optional<Musica> musica){
        if (musica.isEmpty()){
            throw new BandaNotFoundException("Banda nao existe");
        }
        return musica.get();
    }
}
