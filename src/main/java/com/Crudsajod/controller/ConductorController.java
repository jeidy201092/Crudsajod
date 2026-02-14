package com.Crudsajod.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Crudsajod.model.Conductor;
import com.Crudsajod.service.ConductorService;

import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("api/conductor")
public class ConductorController {

    private final ConductorService conductorService;

    public ConductorController(ConductorService conductorService) {
        this.conductorService = conductorService;
    }

    @PostMapping
    public ResponseEntity<Conductor> createConductor(@RequestBody Conductor conductor) {
        Conductor nuevo = conductorService.SaveConductor(conductor);
        URI locatio = URI.create("/api/conductor/" + nuevo.getId());
        return ResponseEntity.created(locatio).body(nuevo);
    }

    @GetMapping
    public ResponseEntity<List<Conductor>> getAllConductor() {
        return ResponseEntity.ok(conductorService.getAllConductor());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Conductor> updateConductor(@PathVariable Long id, @RequestBody Conductor conductor) {
        return ResponseEntity.ok(conductorService.UpdateConductor(id, conductor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConductor(@PathVariable Long id) {
        conductorService.DeleteConductor(id);
        return ResponseEntity.noContent().build();
    }
}