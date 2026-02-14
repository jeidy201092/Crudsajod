package com.Crudsajod.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;







@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ruta")

public class Ruta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idRuta;
    private String origen ;
    private String destino;
    private String descripcion;
    

}
