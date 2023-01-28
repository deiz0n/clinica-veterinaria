package com.clinica_veterinaria.projetopoo.resources;

import com.clinica_veterinaria.projetopoo.entities.AnimalCliente;
import com.clinica_veterinaria.projetopoo.services.ServiceAnimalCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/animalcliente")
public class ResourceAnimalCliente {

    @Autowired
    private ServiceAnimalCliente service;

    // Método GET responsável por realizar a busca por todos os aninalCliente
    @GetMapping
    public ResponseEntity<List<AnimalCliente>> findAll() {
        List<AnimalCliente> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    // Método GET responsável por realizar a busca do animalCLiente por id
    @GetMapping(value = "/{id}")
    public ResponseEntity<AnimalCliente> findById(@PathVariable Long id) {
        AnimalCliente animalCliente = service.findById(id);
        return ResponseEntity.ok().body(animalCliente);
    }

}
