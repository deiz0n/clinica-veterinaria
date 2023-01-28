package com.clinica_veterinaria.projetopoo.resources;

import com.clinica_veterinaria.projetopoo.entities.Agendamento;
import com.clinica_veterinaria.projetopoo.services.ServiceAgendamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class ResourceAgendamento {

    @Autowired
    private ServiceAgendamento service;

    // Método GET responsável por realizar a busca por todos os agendamentos
    @GetMapping
    public ResponseEntity<List<Agendamento>> findALl() {
        List<Agendamento> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    // Método GET responsável por realizar a busca do agendamento por id
    @GetMapping(value = "/{id}")
    public ResponseEntity<Agendamento> findById(@PathVariable Long id) {
        Agendamento agendamento = service.findById(id);
        return ResponseEntity.ok().body(agendamento);
    }

    // Método POST responsável por realizar a adição de um novo agendamento
    @PostMapping
    public ResponseEntity<Agendamento> insert(@RequestBody Agendamento agendamento) {
        agendamento = service.insert(agendamento);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(agendamento.getId()).toUri();
        return ResponseEntity.created(uri).body(agendamento);
    }

    // Método DELETE responsável por realizar a exclusão de um agendamento por id
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Método PUT responsável por realizar a atualização dos dados do agendamento por id
    @PutMapping(value = "/{id}")
    public ResponseEntity<Agendamento> update(@PathVariable Long id, @RequestBody Agendamento agendamento) {
        agendamento = service.update(id, agendamento);
        return ResponseEntity.ok().body(agendamento);
    }

}
