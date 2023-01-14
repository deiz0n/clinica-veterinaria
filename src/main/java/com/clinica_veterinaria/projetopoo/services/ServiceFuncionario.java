package com.clinica_veterinaria.projetopoo.services;

import com.clinica_veterinaria.projetopoo.entities.Funcionario;
import com.clinica_veterinaria.projetopoo.repositories.RepositoryFuncionario;
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
public class ServiceFuncionario {

    @Autowired
    private RepositoryFuncionario repository;

    // Método responsável por realizar a busca de todos os funcionarios
    public List<Funcionario> findAll() {
        return  repository.findAll();
    }

    // Método responsável por realizar a busca do funcionario por id
    public Funcionario findById(Long id) {
        Optional<Funcionario> funcionario = repository.findById(id);
        return funcionario.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    // Método responsável por adicionar um novo funcionario
    public Funcionario insert(Funcionario funcionario) {
        return repository.save(funcionario);
    }

    // Método responsável por excluir um funcionario por id
    public void delete(Long id) {
        try{
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    // Método responsável atualizar os dados do funcionario por id
    public Funcionario update(Long id, Funcionario funcionario) {
        try {
            Funcionario obj = repository.getReferenceById(id);
            updateData(obj, funcionario);
            return repository.save(obj);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }

    }

    // Método responsável por disponibilizar quais os dados do funcionario serão atualizados
    public void updateData(Funcionario obj, Funcionario funcionario) {
        obj.setNome(funcionario.getNome());
        obj.setCpf(funcionario.getCpf());
        obj.setEmail(funcionario.getEmail());
        obj.setSalario(funcionario.getSalario());
        obj.setTelefone(funcionario.getTelefone());
        obj.setEndereco(funcionario.getEndereco());
    }

}
