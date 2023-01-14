package com.clinica_veterinaria.projetopoo.services;

import com.clinica_veterinaria.projetopoo.entities.Servico;
import com.clinica_veterinaria.projetopoo.repositories.RepositoryServico;
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
public class ServiceServico {

    @Autowired
    private RepositoryServico repository;

    // Método responsável por realizar a busca de todos os servicos
    public List<Servico> findAll() {
        return repository.findAll();
    }

    // Método responsável por realizar a busca do servico por id
    public Servico findById(Long id) {
        Optional<Servico> servico = repository.findById(id);
        return servico.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    // Método responsável por adicionar um novo servico
    public Servico insert(Servico servico) {
        return repository.save(servico);
    }


    // Método responsável por excluir um servico por id
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    // Método responsável atualizar os dados do servico por id
    public Servico update(Servico servico, Long id) {
        try {
            Servico obj = repository.getReferenceById(id);
            updateData(obj, servico);
            return repository.save(obj);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    // Método responsável por disponibilizar quais os dados do servico serão atualizados
    public void updateData(Servico obj, Servico servico) {
        obj.setTipo(servico.getTipo());
        obj.setDescricao(servico.getDescricao());
        obj.setPreco(servico.getPreco());
    }

}
