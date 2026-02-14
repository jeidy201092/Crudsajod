package com.Crudsajod.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Crudsajod.dto.RutaDTO;
import com.Crudsajod.model.Ruta;
import com.Crudsajod.service.RutaService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("api/ruta")
public class RutaController {

    private final RutaService rutaService;

    public RutaController(RutaService rutaService) {
        this.rutaService = rutaService;
    }

    @PostMapping
    public ResponseEntity<Ruta> createRuta(@RequestBody RutaDTO rutaDTO) {
        Ruta nuevaRuta = rutaService.saveRuta(rutaDTO);
        return ResponseEntity.ok(nuevaRuta);
    }

    @GetMapping
    public ResponseEntity<List<Ruta>> getAllRutas() {
        return ResponseEntity.ok(rutaService.getAllRuta());
    }

    @PutMapping("/{idRuta}")
    public ResponseEntity<Ruta> updateRuta(@PathVariable Long idRuta, @RequestBody RutaDTO rutaDTO) {
        Ruta rutaActualizada = rutaService.updateRuta(idRuta, rutaDTO);
        return ResponseEntity.ok(rutaActualizada);
    }

    @DeleteMapping("/{idRuta}")
    public ResponseEntity<Void> deleteRuta(@PathVariable Long idRuta) {
        rutaService.deleteRuta(idRuta);
        return ResponseEntity.noContent().build();
    }
}

