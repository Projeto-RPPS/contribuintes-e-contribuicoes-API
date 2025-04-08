package com.rpps.rppsProject;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RppsProjectApplication {

	public static void main(String[] args) {
		// Carrega o .env antes de iniciar o Spring
		Dotenv dotenv = Dotenv.load();

		// Seta as variáveis no sistema
		dotenv.entries().forEach(entry -> {
			System.setProperty(entry.getKey(), entry.getValue());
		});

		SpringApplication.run(RppsProjectApplication.class, args);
	}

}
