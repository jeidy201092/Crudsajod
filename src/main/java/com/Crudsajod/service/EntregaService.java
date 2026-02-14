package com.Crudsajod.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.Crudsajod.dto.EntregaDTO;
import com.Crudsajod.model.Conductor;
import com.Crudsajod.model.Entrega;
import com.Crudsajod.model.Pedido;
import com.Crudsajod.repository.ConductorRepository;
import com.Crudsajod.repository.EntregaRepository;
import com.Crudsajod.repository.PedidoRepository;

@Service
public class EntregaService {


    private EntregaRepository entregaRepository;
    private PedidoRepository pedidoRepository;
    private ConductorRepository conductorRepository;


    public EntregaService(EntregaRepository entregaRepository,PedidoRepository pedidoRepository,ConductorRepository conductorRepository)  {
    this.entregaRepository = entregaRepository;
    this.pedidoRepository = pedidoRepository;
    this.conductorRepository = conductorRepository;
    }


 public Entrega saveEntrega(EntregaDTO entregaDTO) {
        Pedido pedido = pedidoRepository.findById(entregaDTO.getPedidoId())
            .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + entregaDTO.getPedidoId()));

        Conductor conductor = conductorRepository.findById(entregaDTO.getConductorId())
            .orElseThrow(() -> new RuntimeException("Conductor no encontrado con id: " + entregaDTO.getConductorId()));

        Entrega entrega = new Entrega();
        entrega.setFechaEntrega(entregaDTO.getFechaEntrega());
        entrega.setEstado(entregaDTO.getEstado());
        entrega.setPedido(pedido);
        entrega.setConductor(conductor);

        return entregaRepository.save(entrega);
    }

    public List<Entrega> getAllEntregas() {
        return entregaRepository.findAll();
    }

    public Entrega updateEntrega(Long idEntrega, EntregaDTO entregaDTO) {
        return entregaRepository.findById(idEntrega)
            .map(existingEntrega -> {
                Pedido pedido = pedidoRepository.findById(entregaDTO.getPedidoId())
                    .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + entregaDTO.getPedidoId()));

                Conductor conductor = conductorRepository.findById(entregaDTO.getConductorId())
                    .orElseThrow(() -> new RuntimeException("Conductor no encontrado con id: " + entregaDTO.getConductorId()));

                existingEntrega.setFechaEntrega(entregaDTO.getFechaEntrega());
                existingEntrega.setEstado(entregaDTO.getEstado());
                existingEntrega.setPedido(pedido);
                existingEntrega.setConductor(conductor);

                return entregaRepository.save(existingEntrega);
            })
            .orElseThrow(() -> new RuntimeException("Entrega no encontrada con id: " + idEntrega));
    }

    public void deleteEntrega(Long idEntrega) {
        if (!entregaRepository.existsById(idEntrega)) {
            throw new RuntimeException("Entrega no encontrada con id: " + idEntrega);
        }
        entregaRepository.deleteById(idEntrega);
    }
}
