package com.clinica_veterinaria.projetopoo.services;

import com.clinica_veterinaria.projetopoo.entities.Produto;
import com.clinica_veterinaria.projetopoo.repositories.RepositoryProduto;
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
public class ServiceProduto {

    @Autowired
    private RepositoryProduto repository;

    // Método responsável por realizar a busca de todos os produtos
    public List<Produto> findAll() {
        return repository.findAll();
    }

    // Método responsável por realizar a busca de produto por id
    public Produto findById(Long id) {
        Optional<Produto> produto = repository.findById(id);
        return produto.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    // Método responsável por adicionar um novo produto
    public Produto insert(Produto produto) {
        return repository.save(produto);
    }

    // Método responsável por excluir do produto por id
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    // Método responsável atualizar os dados do produto por id
    public Produto update(Long id, Produto produto) {
        try {
            Produto obj = repository.getReferenceById(id);
            updateData(obj, produto);
            return repository.save(obj);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    // Método responsável por disponibilizar quais os dados do produto serão atualizados
    public void updateData(Produto obj, Produto produto) {
        obj.setNome(produto.getNome());
        obj.setPreco(produto.getPreco());
    }

}
