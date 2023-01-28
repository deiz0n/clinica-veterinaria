package com.clinica_veterinaria.projetopoo.config;

import com.clinica_veterinaria.projetopoo.entities.*;
import com.clinica_veterinaria.projetopoo.repositories.*;
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

    @Autowired
    private RepositoryServico repositoryServico;

    @Autowired
    private RepositoryProduto repositoryProduto;

    @Autowired
    private RepositoryAgendamento repositoryAgendamento;

    @Autowired
    private RepositoryAnimalCliente repositoryAnimalCliente;

    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public void run(String... args) throws Exception {

        Cliente client1 = new Cliente(null, "080.325.173-90", "Deizon Deizon", format.parse("18/03/2002"), "deizon@gmail.com", "40028922", "Rua do meio");
        Cliente client2 = new Cliente(null, "095.673.138-10", "Amanda", format.parse("10/07/2003"), "lara@gmail.com", "13245678", "Gama");

        Funcionario func1 = new Funcionario(null, "Eduardo", "132.456.678.11", format.parse("18/12/2006"), "eduardo@gmail.com", 2300.67, "98765432", "Rua General");

        Produto product = new Produto(null, "Galaxy", 1748.34);

        Animal animal1 = new Animal(null, "Sky", "Husky", format.parse("06/09/2010"), "fusca azul", 'M', "Preto");
        Animal animal2 = new Animal(null, "Bob", "Y", format.parse("05/06/2012"), "X", 'F', "Branco");

        Servico service = new Servico(null, "Limpeza", "Banho completo em gato", 100.0);

        repositoryCliente.saveAll(Arrays.asList(client1, client2));
        repositoryFuncionario.save(func1);
        repositoryAnimal.saveAll(Arrays.asList(animal1, animal2));
        repositoryProduto.save(product);
        repositoryServico.save(service);

        AnimalCliente animalCliente = new AnimalCliente(null);

        animalCliente.setCliente(client1);
        animalCliente.setAnimal(animal1);

        repositoryAnimalCliente.save(animalCliente);

        Agendamento agen = new Agendamento(null, format.parse("18/05/2018"), "Realizado");

        agen.setServico(service);
        agen.setAnimal(animal1);
        agen.setFuncionario(func1);

        repositoryAgendamento.save(agen);
    }
}
