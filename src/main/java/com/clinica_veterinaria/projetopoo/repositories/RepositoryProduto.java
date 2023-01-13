package com.clinica_veterinaria.projetopoo.repositories;

import com.clinica_veterinaria.projetopoo.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryProduto extends JpaRepository<Produto, Long> {
}
