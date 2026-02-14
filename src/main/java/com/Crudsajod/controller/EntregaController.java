package com.Crudsajod.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Crudsajod.dto.EntregaDTO;
import com.Crudsajod.model.Entrega;
import com.Crudsajod.service.EntregaService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("api/entrega")
public class EntregaController {

    private final EntregaService entregaService;

    public EntregaController(EntregaService entregaService) {
        this.entregaService = entregaService;
    }

    @PostMapping
    public ResponseEntity<Entrega> createEntrega(@RequestBody EntregaDTO entregaDTO) {
        Entrega nuevaEntrega = entregaService.saveEntrega(entregaDTO);
        return ResponseEntity.ok(nuevaEntrega);
    }

    @GetMapping
    public ResponseEntity<List<Entrega>> getAllEntregas() {
        return ResponseEntity.ok(entregaService.getAllEntregas());
    }


    @PutMapping("/{idEntrega}")
    public ResponseEntity<Entrega> updateEntrega(@PathVariable Long idEntrega, @RequestBody EntregaDTO entregaDTO) {
        Entrega entregaActualizada = entregaService.updateEntrega(idEntrega, entregaDTO);
        return ResponseEntity.ok(entregaActualizada);
    }

    @DeleteMapping("/{idEntrega}")
    public ResponseEntity<Void> deleteEntrega(@PathVariable Long idEntrega) {
        entregaService.deleteEntrega(idEntrega);
        return ResponseEntity.noContent().build();
    }
}