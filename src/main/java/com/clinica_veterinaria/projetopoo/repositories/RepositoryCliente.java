package com.clinica_veterinaria.projetopoo.repositories;

import com.clinica_veterinaria.projetopoo.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryCliente extends JpaRepository<Cliente, Long> {
}
