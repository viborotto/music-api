package com.viborotto.musicapi.controller;

import com.viborotto.musicapi.model.Banda;
import com.viborotto.musicapi.service.impl.BandaServiceImpl;
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
    public ResponseEntity<?> getList() {
        return ResponseEntity.status(HttpStatus.OK).body(bandaService.listarBandas());
    }

    @GetMapping("/banda/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(bandaService.getBanda(id));
    }

    //    @GetMapping("/banda/{nome}")
    //    public ResponseEntity<?> getByNome(@PathVariable String nome) throws Exception {
    //        return ResponseEntity.status(HttpStatus.OK).body(bandaService.porNome(nome));
    //    }

    @PostMapping("/banda/nova_banda")
    public ResponseEntity<?> postBanda(@RequestBody Banda banda) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bandaService.criarBanda(banda));
    }

    @PatchMapping("/banda/{id}/update")
    public ResponseEntity<?> updateBanda(@PathVariable Long id, @RequestBody Banda banda){
        return ResponseEntity.status(HttpStatus.OK).body(bandaService.atualizarBanda(id, banda));
    }

    @DeleteMapping("/banda/{id}/delete")
    public void deleteBanda(@PathVariable Long id){
        bandaService.deletarBanda(id);
    }
}
