package com.clinica_veterinaria.projetopoo.config;

import com.clinica_veterinaria.projetopoo.entities.Animal;
import com.clinica_veterinaria.projetopoo.entities.Cliente;
import com.clinica_veterinaria.projetopoo.entities.Funcionario;
import com.clinica_veterinaria.projetopoo.repositories.RepositoryAnimal;
import com.clinica_veterinaria.projetopoo.repositories.RepositoryCliente;
import com.clinica_veterinaria.projetopoo.repositories.RepositoryFuncionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestApplication implements CommandLineRunner {

    @Autowired
    private RepositoryCliente repositoryCliente;

    @Autowired
    private RepositoryFuncionario repositoryFuncionario;

    @Autowired
    private RepositoryAnimal repositoryAnimal;

    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public void run(String... args) throws Exception {

        Cliente client1 = new Cliente(null, "080.325.173-90", "Deizon Deizon", format.parse("18/03/2002"), "deizon@gmail.com", "40028922", "Rua do meio");
        Cliente client2 = new Cliente(null, "095.673.138-10", "Amanda", format.parse("10/07/2003"), "lara@gmail.com", "13245678", "Gama");

        Funcionario func1 = new Funcionario(null, "132.456.678.11", "Eduardo", format.parse("18/12/2006"), "eduardo@gmail.com", 2300.67, "98765432", "Rua General");

        Animal animal1 = new Animal(null, "Sky", "Husky", format.parse("6/9/2010"), "fusca azul", 'M', "Preto");

        repositoryCliente.saveAll(Arrays.asList(client1, client2));
        repositoryFuncionario.save(func1);
        repositoryAnimal.save(animal1);


    }
}
