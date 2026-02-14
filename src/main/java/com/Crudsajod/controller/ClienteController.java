package com.Crudsajod.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Crudsajod.model.Cliente;
import com.Crudsajod.service.ClienteService;

import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController  // Indicates that this class handles REST API requests
@RequestMapping("api/cliente")  // Base URL path for all endpoints in this controller

public class ClienteController {
    private final ClienteService clienteService; // Service layer dependency for business logic

  // Constructor injection for ClienteService
    public ClienteController(ClienteService clienteService){
        this.clienteService =clienteService;
    }
    @PostMapping  // Handles POST requests to create a new Cliente
    public  ResponseEntity<Cliente> createCliente(@RequestBody  Cliente cliente) {  // @RequestBody binds JSON to Cliente object
        Cliente nuevo = clienteService.SaveCliente(cliente); // Calls service to save the new client
        URI locatio = URI.create("/api/cliente/" + nuevo.getId()); // Creates URI for the newly created resource
        return ResponseEntity.created(locatio).body(nuevo);// Returns 201 Created with the new client in the body
    }
    
    
    @GetMapping  // Handles GET requests to retrieve all clients
    
    public  ResponseEntity<List<Cliente>> getAllCliente() {
        return ResponseEntity.ok(clienteService.getAllCliente()); // Returns HTTP 200 with list of clients
    }

    @PutMapping("/{id}") // Handles PUT requests to update a client by ID
    public  ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody  Cliente cliente) {
        return ResponseEntity .ok(clienteService.UpdateCliente(id, cliente)); // Returns updated client with status 200
    }

@DeleteMapping("/{id}") // Handles DELETE requests to remove a client by ID
public ResponseEntity<Void> deleteCliente(@PathVariable Long id){
    clienteService.DeleteCliente(id); // Calls service to delete client
    return ResponseEntity.noContent().build(); // Returns HTTP 204 No Content
    }
}

