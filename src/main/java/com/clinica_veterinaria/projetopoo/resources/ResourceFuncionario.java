package com.clinica_veterinaria.projetopoo.resources;

import com.clinica_veterinaria.projetopoo.entities.Funcionario;
import com.clinica_veterinaria.projetopoo.services.ServiceFuncionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/funcionarios")
public class ResourceFuncionario {

    @Autowired
    private ServiceFuncionario service;

    // Método GET responsável por realizar a busca por todos os funcionarios
    @GetMapping
    public ResponseEntity<List<Funcionario>> findAll() {
        List<Funcionario> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    // Método GET responsável por realizar a busca do funcionario por id
    @GetMapping(value = "/{id}")
    public ResponseEntity<Funcionario> findById(@PathVariable Long id) {
        Funcionario funcionario = service.findById(id);
        return ResponseEntity.ok().body(funcionario);
    }

    // Método POST responsável por realizar a adição de um novo funcionario
    @PostMapping
    public ResponseEntity<Funcionario> insert(@RequestBody Funcionario funcionario) {
        funcionario = service.insert(funcionario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(funcionario.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    // Método DELETE responsável por realizar a exclusão de um funcionario por id
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Método PUT responsável por realizar a atualização dos dados do funcionario por id
    @PutMapping(value = "/{id}")
    public ResponseEntity<Funcionario> update(@PathVariable Long id, @RequestBody Funcionario funcionario) {
        funcionario = service.update(id, funcionario);
        return ResponseEntity.ok().body(funcionario);
    }
}
