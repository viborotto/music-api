package com.viborotto.musicapi.service;

import com.viborotto.musicapi.model.Album;
import com.viborotto.musicapi.model.Musica;

import java.util.List;

public interface MusicaService {

//    List<Musica> listarByBanda();

    List<Musica> findByAlbum(Long idAlbum);

    Musica getByIdAndAlbum(Long idMusica, Album album);

    Musica inserirNovaMusicaNoAlbum(Long idAlbum, Musica musica);

    Musica atualizarMusicaDoAlbum(Long idMusica, Musica musica);

    void deletarMusicaDoAlbum(Long idMusica);
}
