package com.viborotto.musicapi.service;

import com.viborotto.musicapi.model.Album;
import com.viborotto.musicapi.model.Banda;

import java.util.List;

public interface AlbumService {

    List<Album> listarByBanda(Long idBanda);

    Album getByIdAndBanda(Long idAlbum, Banda banda);

    Album inserirNovoAlbumNaBanda(Long idBanda, Album album);

    Album atualizarAlbumdaBanda(Long idAlbum, Album album);

    void deletarAlbumDaBanda(Long idAlbum);
}
