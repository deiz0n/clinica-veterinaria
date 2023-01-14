package com.clinica_veterinaria.projetopoo.repositories;

import com.clinica_veterinaria.projetopoo.entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryFuncionario extends JpaRepository<Funcionario, Long> {
}
