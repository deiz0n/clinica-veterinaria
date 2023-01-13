package com.clinica_veterinaria.projetopoo.config;

import com.clinica_veterinaria.projetopoo.entities.Cliente;
import com.clinica_veterinaria.projetopoo.repositories.RepositoryCliente;
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
    private RepositoryCliente repository;

    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public void run(String... args) throws Exception {

        Cliente client1 = new Cliente(null, "080.325.173-90", "Deizon Deizon", format.parse("18/03/2002"), "deizon@gmail.com", "40028922", "Rua do meio");
        Cliente client2 = new Cliente(null, "095.673.138-10", "Lara R", format.parse("10/07/2003"), "lara@gmail.com", "13245678", "Gama");

        repository.saveAll(Arrays.asList(client1, client2));

    }
}
