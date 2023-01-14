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

    // Método responsável por realizar a busca de todos os clientes
    public List<Cliente> findAll() {
        return repository.findAll();
    }

    // Método responsável por realizar a busca de cliente por id
    public Cliente findById(Long id) {
        Optional<Cliente> cliente = repository.findById(id);
        return cliente.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    // Método responsável por adicionar um novo cliente
    public Cliente insert(Cliente cliente) {
        return repository.save(cliente);
    }

    // Método responsável por excluir um cliente por id
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    // Método responsável atualizar os dados do cliente por id
    public Cliente update(Long id, Cliente cliente) {
        try {
            Cliente obj = repository.getReferenceById(id);
            updateData(obj, cliente);
            return repository.save(obj);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    // Método responsável por disponibilizar quais os dados do cliente serão atualizados
    private void updateData(Cliente obj, Cliente cliente) {
        obj.setCpf(cliente.getCpf());
        obj.setNome(cliente.getNome());
        obj.setEmail(cliente.getEmail());
        obj.setEndereco(cliente.getEndereco());
        obj.setTelefone(cliente.getTelefone());
    }
}
