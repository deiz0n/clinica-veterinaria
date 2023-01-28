package com.clinica_veterinaria.projetopoo.services;

import com.clinica_veterinaria.projetopoo.entities.Agendamento;
import com.clinica_veterinaria.projetopoo.repositories.RepositoryAgendamento;
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
public class ServiceAgendamento {

    @Autowired
    private RepositoryAgendamento repository;

    // Método responsável por realizar a busca de todos os agendamentos
    public List<Agendamento> findAll() {
        return repository.findAll();
    }

    // Método responsável por realizar a busca do agendamento por id
    public Agendamento findById(Long id) {
        Optional<Agendamento> agendamento = repository.findById(id);
        return agendamento.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    // Método responsável por adicionar um novo agendamento
    public Agendamento insert(Agendamento agendamento) {
        return agendamento = repository.save(agendamento);
    }

    // Método responsável por excluir um agendamento por id
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    // Método responsável atualizar os dados do agendamento por id
    public Agendamento update(Long id, Agendamento agendamento) {
        try {
            Agendamento obj = repository.getReferenceById(id);
            updateData(obj, agendamento);
            return repository.save(obj);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    // Método responsável por disponibilizar quais os dados do agendamento serão atualizados
    public void updateData(Agendamento obj, Agendamento agendamento) {
        obj.setFuncionario(agendamento.getFuncionario());
        obj.setData(agendamento.getData());
        obj.setSituacao(agendamento.getSituacao());
    }
}
