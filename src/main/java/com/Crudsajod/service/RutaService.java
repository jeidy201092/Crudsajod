package com.Crudsajod.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.Crudsajod.dto.RutaDTO;
import com.Crudsajod.model.Ruta;
import com.Crudsajod.repository.RutaRepository;


@Service
public class RutaService {

    private  RutaRepository rutaRepository;

    public RutaService(RutaRepository rutaRepository) {
        this.rutaRepository = rutaRepository;
    }

public Ruta saveRuta(RutaDTO rutaDTO) {
        Ruta ruta = new Ruta();
        ruta.setOrigen(rutaDTO.getOrigen());
        ruta.setDestino(rutaDTO.getDestino());
        ruta.setDescripcion(rutaDTO.getDescripcion());
        return rutaRepository.save(ruta);
    }

    public List<Ruta> getAllRuta() {
        return rutaRepository.findAll();
    }

public Ruta updateRuta(Long idRuta, RutaDTO rutaDTO) {
        return rutaRepository.findById(idRuta)
            .map(existingRuta -> {
                existingRuta.setOrigen(rutaDTO.getOrigen());
                existingRuta.setDestino(rutaDTO.getDestino());
                existingRuta.setDescripcion(rutaDTO.getDescripcion());
                return rutaRepository.save(existingRuta);
            })
            .orElseThrow(() -> new RuntimeException("Ruta no encontrada con id: " + idRuta));
    }

    public void deleteRuta(Long idRuta) {
        if (!rutaRepository.existsById(idRuta)) {
            throw new RuntimeException("Ruta no encontrada con id: " + idRuta);
        }
        rutaRepository.deleteById(idRuta);
    }
}



