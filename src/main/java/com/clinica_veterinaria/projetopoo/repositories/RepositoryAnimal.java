package com.clinica_veterinaria.projetopoo.repositories;

import com.clinica_veterinaria.projetopoo.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryAnimal extends JpaRepository<Animal, Long> {
}
