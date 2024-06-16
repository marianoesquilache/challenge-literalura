package com.mariano.literalura.Literalura;

import com.mariano.literalura.Literalura.main.AppMain;
import com.mariano.literalura.Literalura.repository.AutorRepository;
import com.mariano.literalura.Literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private AppMain appMain; // Inyectamos AppMain

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		appMain.mostrarMenu();
	}
}
