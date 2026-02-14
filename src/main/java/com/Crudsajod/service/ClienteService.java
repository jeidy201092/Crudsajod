package com.Crudsajod.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.Crudsajod.model.Cliente;
import com.Crudsajod.repository.ClienteRepository;


@Service  // Marks this class as a Spring service component
public class ClienteService {


    private ClienteRepository clienteRepository;  // Repository for database operations

     // Constructor injection of ClienteRepository
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

     // Saves a new client into the database
    public Cliente SaveCliente (Cliente cliente) {
        return clienteRepository.save(cliente); // Persists and returns the saved Cliente

    }
     // Retrieves all clients from the database
    public List<Cliente> getAllCliente(){
        return clienteRepository.findAll(); // Returns list of all Cliente records
    }

    // Updates an existing client identified by idCliente
    public Cliente UpdateCliente (Long idCliente, Cliente cliente){
        return clienteRepository.findById(idCliente) // Searches for the client by ID
        
            .map (existingCliente -> { // If found, updates fields
                existingCliente.setName(cliente.getName());  // Updates name
                existingCliente.setApellido(cliente.getApellido()); // Updates last name
                existingCliente.setTelefono(cliente.getTelefono()); // Updates phone
                existingCliente.setDireccion(cliente.getDireccion()); // Updates address
                existingCliente.setCorreo(cliente.getCorreo());  // Updates email
                 return clienteRepository.save(existingCliente); // Saves and returns updated Cliente
            } )
            // Throws an exception if the client does not exist
            .orElseThrow(() -> new RuntimeException("Cliente not found with idCliente" + idCliente));

    }
    // Deletes a client by ID

    public void DeleteCliente(Long idCliente) {
          // Checks if the client exists
        if(!clienteRepository.existsById(idCliente)) {
            throw new  RuntimeException("Cliente not found with idCliente" + idCliente);
        
        }
        clienteRepository.deleteById(idCliente);  // Deletes the client from the database
    }
}
