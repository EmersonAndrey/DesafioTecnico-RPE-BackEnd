package com.rpe.desafioTecnico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DesafioTecnicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioTecnicoApplication.class, args);
	}

}
