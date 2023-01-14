package com.clinica_veterinaria.projetopoo.resources;

import com.clinica_veterinaria.projetopoo.entities.Servico;
import com.clinica_veterinaria.projetopoo.services.ServiceServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/servicos")
public class ResourceServico {

    @Autowired
    private ServiceServico service;

    // Método GET responsável por realizar a busca por todos os servicos
    @GetMapping
    public ResponseEntity<List<Servico>> findAll() {
        List<Servico> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    // Método GET responsável por realizar a busca do servico por id
    @GetMapping(value = "/{id}")
    public ResponseEntity<Servico> findById(@PathVariable Long id) {
        Servico servico = service.findById(id);
        return ResponseEntity.ok().body(servico);
    }

    // Método POST responsável por realizar a adição de um novo servico
    @PostMapping
    public ResponseEntity<Servico> insert(@RequestBody Servico servico) {
        servico = service.insert(servico);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(servico.getId()).toUri();
        return ResponseEntity.created(uri).body(servico);
    }

    // Método DELETE responsável por realizar a exclusão de um servico por id
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


    // Método PUT responsável por realizar a atualização dos dados do servico por id
    @PutMapping(value = "/{id}")
    public ResponseEntity<Servico> update(@PathVariable Long id, @RequestBody Servico servico) {
        servico = service.update(servico, id);
        return ResponseEntity.ok().body(servico);
    }
}
