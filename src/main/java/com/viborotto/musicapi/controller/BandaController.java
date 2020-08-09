package com.viborotto.musicapi.controller;

import com.viborotto.musicapi.model.Banda;
import com.viborotto.musicapi.service.BandaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class BandaController {

    @Autowired
    private BandaServiceImpl bandaService;

    @GetMapping("/bandas")
    public ResponseEntity<?> getList() throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(bandaService.listarBandas());
    }

    @GetMapping("/banda/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(bandaService.porId(id));
    }

    //    @GetMapping("/banda/{nome}")
    //    public ResponseEntity<?> getByNome(@PathVariable String nome) throws Exception {
    //        return ResponseEntity.status(HttpStatus.OK).body(bandaService.porNome(nome));
    //    }

    @PostMapping("/banda/nova_banda")
    public ResponseEntity<?> postBanda(@RequestBody Banda banda) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(bandaService.criarBanda(banda));
    }
}
