package com.clinica_veterinaria.projetopoo.services;

import com.clinica_veterinaria.projetopoo.entities.AnimalCliente;
import com.clinica_veterinaria.projetopoo.repositories.RepositoryAnimalCliente;
import com.clinica_veterinaria.projetopoo.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceAnimalCliente {

    @Autowired
    private RepositoryAnimalCliente  repository;

    // Método responsável por realizar a busca de todos os animalCLiente
    public List<AnimalCliente> findAll() {
        return repository.findAll();
    }

    // Método responsável por realizar a busca do animalCliente por id
    public AnimalCliente findById(Long id) {
        Optional<AnimalCliente> animalCliente = repository.findById(id);
        return animalCliente.orElseThrow(() -> new ResourceNotFoundException(id));
    }
}
