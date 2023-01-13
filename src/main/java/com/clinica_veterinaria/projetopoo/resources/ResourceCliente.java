package com.clinica_veterinaria.projetopoo.resources;

import com.clinica_veterinaria.projetopoo.entities.Cliente;
import com.clinica_veterinaria.projetopoo.services.ServiceCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ResourceCliente {

    @Autowired
    private ServiceCliente service;

    // Método GET responsável por realizar a busca por todos os clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> findAll() {
        List<Cliente> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    // Método GET responsável por realizar a busca do cliente por id
    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {
        Cliente cliente = service.findById(id);
        return ResponseEntity.ok().body(cliente);
    }

    // Método POST responsável por realizar a adição de um novo cliente
    @PostMapping
    public ResponseEntity<Cliente> insert(@RequestBody Cliente cliente) {
        cliente = service.insert(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(cliente);
    }

    // Método DELETE responsável por realizar a exclusão de um cliente por id
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


    // Método PUT responsável por realizar a atualização dos dados do cliente por id
    @PutMapping(value = "/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente cliente) {
        cliente = service.update(id, cliente);
        return ResponseEntity.ok().body(cliente);
    }

}
