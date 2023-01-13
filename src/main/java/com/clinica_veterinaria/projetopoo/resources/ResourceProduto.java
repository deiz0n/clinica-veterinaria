package com.clinica_veterinaria.projetopoo.resources;

import com.clinica_veterinaria.projetopoo.entities.Produto;
import com.clinica_veterinaria.projetopoo.services.ServiceProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ResourceProduto {

    @Autowired
    private ServiceProduto service;

    // Método GET responsável por realizar a busca por todos os produtos
    @GetMapping
    public ResponseEntity<List<Produto>> findAll() {
        List<Produto> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    // Método GET responsável por realizar a busca do produto por id
    @GetMapping(value = "/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Long id) {
        Produto produto = service.findById(id);
        return ResponseEntity.ok().body(produto);
    }

    // Método POST responsável por realizar a adição de um novo produto
    @PostMapping
    public ResponseEntity<Produto> insert(@RequestBody Produto produto) {
        produto = service.insert(produto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(produto);
    }

    // Método DELETE responsável por realizar a exclusão de um produto por id
    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Método PUT responsável por realizar a atualização dos dados do produto por id
    @PutMapping(value = "/{id}")
    public ResponseEntity<Produto> update(@PathVariable Long id, @RequestBody Produto produto) {
        produto = service.update(id, produto);
        return ResponseEntity.ok().body(produto);
    }

}
