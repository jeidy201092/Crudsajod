package com.Crudsajod.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.Crudsajod.model.Conductor;
import com.Crudsajod.repository.ConductorRepository;

@Service
public class ConductorService {

    
    private ConductorRepository conductorRepository;


    public ConductorService(ConductorRepository conductorRepository){
        this.conductorRepository =conductorRepository;
    }



    public Conductor SaveConductor (Conductor conductor) {
        return conductorRepository.save(conductor);

    }
    public List<Conductor> getAllConductor(){
        return conductorRepository.findAll();
    }

    public Conductor UpdateConductor (Long id, Conductor conductor){
        return conductorRepository.findById(id)
        
            .map (existingConductor -> {
                existingConductor.setName(conductor.getName());
                existingConductor.setApellido(conductor.getApellido());
                existingConductor.setTelefono(conductor.getTelefono());
                existingConductor.setDireccion(conductor.getDireccion());
                existingConductor.setEmail(conductor.getEmail());
                 return conductorRepository.save(existingConductor); // <- return el conductor garda0//
            } )
            .orElseThrow(() -> new RuntimeException("Conductor not found with idConductor" + id));

    }

    public void DeleteConductor(Long id) {
        if(!conductorRepository.existsById(id)) {
            throw new  RuntimeException("Conductor not found with idConductor" + id);
        
    }
    conductorRepository.deleteById(id);
}
}



