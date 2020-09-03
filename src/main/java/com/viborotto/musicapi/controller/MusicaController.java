package com.viborotto.musicapi.controller;

import com.viborotto.musicapi.model.Album;
import com.viborotto.musicapi.model.Banda;
import com.viborotto.musicapi.model.Musica;
import com.viborotto.musicapi.service.AlbumService;
import com.viborotto.musicapi.service.BandaService;
import com.viborotto.musicapi.service.MusicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bandas")
public class MusicaController {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private MusicaService musicaService;

    @Autowired
    private BandaService bandaService;

    @GetMapping("/{bandaId}/albums/{albumId}/musicas")
    public ResponseEntity<?> list(@PathVariable Long bandaId, @PathVariable Long albumId){
        return ResponseEntity.ok(musicaService.findByAlbum(albumId));
    }

    @GetMapping("/{bandaId}/albums/{albumId}/musicas/{musicaId}")
    public ResponseEntity<?> getMusica(@PathVariable Long bandaId, @PathVariable Long albumId, @PathVariable Long musicaId){
        Banda banda = bandaService.getBanda(bandaId);
        Album album = albumService.getByIdAndBanda(bandaId, banda);
        Musica musica = musicaService.getByIdAndAlbum(albumId, album);
        return ResponseEntity.ok(musica);
    }

    @PostMapping("/{bandaId}/albums/{albumId}/musicas")
    public ResponseEntity<?> createMusica(@PathVariable Long bandaId, @PathVariable Long albumId, @RequestBody Musica musica){
        Banda banda = bandaService.getBanda(bandaId);
        Album album = albumService.getByIdAndBanda(bandaId, banda);
        return ResponseEntity.ok(musicaService.inserirNovaMusicaNoAlbum(albumId, musica));
    }

    //TODO : arrumar o patch(nao esta funcionando)
    @PatchMapping("/{bandaId}/albums/{albumId}/musicas/{musicaId}")
    public ResponseEntity<?> updateAlbum(@PathVariable Long bandaId,@PathVariable Long albumId, @RequestBody Musica musica, @PathVariable Long musicaId){
        return ResponseEntity.ok(musicaService.atualizarMusicaDoAlbum(musicaId,musica));
    }

    @DeleteMapping("/{bandaId}/albums/{albumId}/musicas/{musicaId}")
    public ResponseEntity<?> deleteMusica(@PathVariable Long bandaId, @PathVariable Long albumId, @PathVariable Long musicaId){
        musicaService.deletarMusicaDoAlbum(musicaId);
        return ResponseEntity.ok().build();
    }
}
