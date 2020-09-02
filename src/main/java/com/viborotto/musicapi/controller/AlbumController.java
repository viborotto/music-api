package com.viborotto.musicapi.controller;

import com.viborotto.musicapi.model.Album;
import com.viborotto.musicapi.model.Banda;
import com.viborotto.musicapi.service.AlbumService;
import com.viborotto.musicapi.service.BandaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bandas")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private BandaService bandaService;

    @GetMapping("/{bandaId}/albums")
    public ResponseEntity<?> getAlbumsByBanda(@PathVariable Long bandaId){
        return ResponseEntity.ok(albumService.listarByBanda(bandaId));
    }

    @GetMapping("/{bandaId}/albums/{albumId}")
    public ResponseEntity<?> getAlbum(@PathVariable Long bandaId, @PathVariable Long albumId){
        Banda banda = bandaService.getBanda(bandaId);
        Album album = albumService.getByIdAndBanda(bandaId, banda);
        return ResponseEntity.ok(album);
    }


    @PostMapping("/{bandaId}/albums")
    public ResponseEntity<?> getAlbumsByBanda(@PathVariable Long bandaId, @RequestBody Album album){
        return ResponseEntity.ok(albumService.inserirNovoAlbumNaBanda(bandaId, album));
    }


    @PatchMapping("/{bandaId}/albums/{albumId}")
    public ResponseEntity<?> updateAlbum(@PathVariable Long bandaId,@PathVariable Long albumId, @RequestBody Album album){
        return ResponseEntity.ok(albumService.atualizarAlbumdaBanda(albumId, album));
    }

    @DeleteMapping("/{bandaId}/albums/{albumId}")
    public ResponseEntity<?> deleteAlbum(@PathVariable Long bandaId,@PathVariable Long albumId){
        albumService.deletarAlbumDaBanda(albumId);
        return ResponseEntity.ok().build();
    }
}
