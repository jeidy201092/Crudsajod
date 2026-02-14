package com.Crudsajod.repository;

// Importing the JpaRepository interface from Spring Data JPA

import org.springframework.data.jpa.repository.JpaRepository;

// Importing the Cliente entity class
import com.Crudsajod.model.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente , Long> {

    /**
 * ClienteRepository is an interface that extends JpaRepository.
 *
 * This repository interface provides CRUD operations and additional
 * JPA-related methods for the Cliente entity, without the need
 * to implement them manually.
 *
 * Parameters:
 * - Cliente: The entity type managed by the repository.
 * - Long: The type of the entity's primary key.
 */
}
