package com.clinica_veterinaria.projetopoo.services;

import com.clinica_veterinaria.projetopoo.entities.Cliente;
import com.clinica_veterinaria.projetopoo.repositories.RepositoryCliente;
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
public class ServiceCliente {

    @Autowired
    private RepositoryCliente repository;

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente findById(Long id) {
        Optional<Cliente> cliente = repository.findById(id);
        return cliente.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Cliente insert(Cliente cliente) {
        return repository.save(cliente);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Cliente update(Long id, Cliente cliente) {
        try {
            Cliente obj = repository.getReferenceById(id);
            updateData(obj, cliente);
            return repository.save(obj);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Cliente obj, Cliente cliente) {
        obj.setNome(cliente.getNome());
        obj.setEmail(cliente.getEmail());
        obj.setEndereco(cliente.getEndereco());
        obj.setTelefone(cliente.getTelefone());
    }
}
