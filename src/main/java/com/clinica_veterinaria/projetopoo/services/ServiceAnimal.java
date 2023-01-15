package com.clinica_veterinaria.projetopoo.services;

import com.clinica_veterinaria.projetopoo.entities.Animal;
import com.clinica_veterinaria.projetopoo.repositories.RepositoryAnimal;
import com.clinica_veterinaria.projetopoo.services.exceptions.DatabaseException;
import com.clinica_veterinaria.projetopoo.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceAnimal {

    @Autowired
    private RepositoryAnimal repository;

    // Método responsável por realizar a busca de todos os animais
    public List<Animal> findAll() {
        return repository.findAll();
    }

    // Método responsável por realizar a busca do animal por id
    public Animal findById(Long id) {
        Optional<Animal> animal = repository.findById(id);
        return animal.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    // Método responsável por adicionar um novo animal
    public Animal insert(Animal animal) {
        return animal = repository.save(animal);
    }

    // Método responsável por excluir um animal por id
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    // Método responsável atualizar os dados do animal por id
    public Animal update(Long id, Animal animal){
        try {
            Animal obj = repository.getReferenceById(id);
            updateData(obj, animal);
            return repository.save(obj);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    // Método responsável por disponibilizar quais os dados do animal serão atualizados
    public void updateData(Animal obj, Animal animal) {
        obj.setNome(animal.getNome());
    }

}
