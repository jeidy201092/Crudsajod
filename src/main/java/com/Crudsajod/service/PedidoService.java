package com.Crudsajod.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.Crudsajod.dto.PedidoDTO;
import com.Crudsajod.model.Cliente;
import com.Crudsajod.model.Pedido;
import com.Crudsajod.repository.ClienteRepository;
import com.Crudsajod.repository.PedidoRepository;


@Service
public class PedidoService {


    private  PedidoRepository pedidoRepository;
    private ClienteRepository clienteRepository;

    public PedidoService(PedidoRepository pedidoRepository, ClienteRepository clienteRepository){
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
    }
    public Pedido savePedido(PedidoDTO pedidoDTO) {
        Cliente cliente =clienteRepository.findById(pedidoDTO.getClienteId())
        .orElseThrow(()  -> new RuntimeException("Cliente no encontrado con id:" + pedidoDTO.getClienteId()));
        Pedido pedido= new Pedido();
        pedido.setEstado(pedidoDTO.getEstado());
        pedido.setFecha(pedidoDTO.getFecha());
        pedido.setCliente(cliente);
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> getAllPedido() {
        return pedidoRepository.findAll();
    }


    public Pedido updatePedido(Long idPedido, PedidoDTO pedidoDTO) {
        return pedidoRepository.findById(idPedido)

            .map(existingPedido -> {
                Cliente cliente = clienteRepository.findById(pedidoDTO.getClienteId())
                .orElseThrow(() ->new RuntimeException("Cliente no encontrado con id: " + pedidoDTO.getClienteId()));
                
                existingPedido.setFecha(pedidoDTO.getFecha());
                existingPedido.setEstado(pedidoDTO.getEstado());
                existingPedido.setCliente(cliente);

                return pedidoRepository.save(existingPedido);
            })
            .orElseThrow(() -> new RuntimeException("Pedido not found with id " + idPedido));
    }

    public void DeletePedido(Long idPedido) {
        if (!pedidoRepository.existsById(idPedido)) {
            throw new RuntimeException("Pedido not found with id " + idPedido);
        }
        pedidoRepository.deleteById(idPedido);
    }
}
