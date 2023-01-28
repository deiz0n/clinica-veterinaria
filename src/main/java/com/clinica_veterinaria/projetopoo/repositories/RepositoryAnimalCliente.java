package com.clinica_veterinaria.projetopoo.repositories;

import com.clinica_veterinaria.projetopoo.entities.AnimalCliente;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface RepositoryAnimalCliente extends JpaRepositoryImplementation<AnimalCliente, Long> {
}
