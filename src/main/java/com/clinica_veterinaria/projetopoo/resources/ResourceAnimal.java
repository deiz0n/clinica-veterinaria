package com.clinica_veterinaria.projetopoo.resources;

import com.clinica_veterinaria.projetopoo.entities.Animal;
import com.clinica_veterinaria.projetopoo.services.ServiceAnimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/animais")
public class ResourceAnimal {

    @Autowired
    private ServiceAnimal service;

    // Método GET responsável por realizar a busca por todos os animais
    @GetMapping
    public ResponseEntity<List<Animal>> findAll() {
        List<Animal> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    // Método GET responsável por realizar a busca do animal por id
    @GetMapping(value = "/{id}")
    public ResponseEntity<Animal> findById(@PathVariable Long id) {
        Animal animal = service.findById(id);
        return ResponseEntity.ok().body(animal);
    }

    // Método POST responsável por realizar a adição de um novo animal
    @PostMapping
    public ResponseEntity<Animal> insert(@RequestBody Animal animal) {
        animal = service.insert(animal);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(animal.getId()).toUri();
        return ResponseEntity.created(uri).body(animal);
    }

    // Método DELETE responsável por realizar a exclusão de um animal por id
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Método PUT responsável por realizar a atualização dos dados do animal por id
    @PutMapping(value = "/{id}")
    public ResponseEntity<Animal> update(@PathVariable Long id, @RequestBody Animal animal) {
        animal = service.update(id, animal);
        return ResponseEntity.ok().body(animal);
    }

}
