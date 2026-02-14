package com.Crudsajod.controller;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.Crudsajod.dto.PedidoDTO;
import com.Crudsajod.model.Pedido;
import com.Crudsajod.service.PedidoService;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/pedido")
public class PedidoController {

    private final PedidoService pedidoService;
    public PedidoController(PedidoService pedidoService){
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<Pedido> createPedido(@RequestBody PedidoDTO pedidoDTO) {
        Pedido nuevoPedido =pedidoService.savePedido(pedidoDTO);
        return ResponseEntity.ok(nuevoPedido);
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> getAllPedido() {
        return ResponseEntity.ok(pedidoService.getAllPedido());
    }

    @PutMapping("/{idPedido}")
    public ResponseEntity<Pedido> updatePedido(@PathVariable Long idPedido, @RequestBody PedidoDTO pedidoDTO) {
        Pedido pedidoActualizado =pedidoService.updatePedido(idPedido, pedidoDTO);
        return ResponseEntity.ok(pedidoActualizado);
    }

    @DeleteMapping("/{idPedido}")
    public ResponseEntity<Void> deletePedido(@PathVariable Long idPedido) {
        pedidoService.DeletePedido(idPedido);
        return ResponseEntity.noContent().build();
    }
}