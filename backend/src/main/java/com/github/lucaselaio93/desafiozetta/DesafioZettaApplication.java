package com.github.lucaselaio93.desafiozetta;

import com.github.lucaselaio93.desafiozetta.model.entity.Cargo;
import com.github.lucaselaio93.desafiozetta.model.entity.Usuario;
import com.github.lucaselaio93.desafiozetta.model.repository.CargoRepository;
import com.github.lucaselaio93.desafiozetta.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class DesafioZettaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioZettaApplication.class, args);
	}

}
