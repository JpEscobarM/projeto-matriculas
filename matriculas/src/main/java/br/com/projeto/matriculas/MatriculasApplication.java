package br.com.projeto.matriculas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MatriculasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatriculasApplication.class, args);
	}

}
