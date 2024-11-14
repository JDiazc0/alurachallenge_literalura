package com.alurachallenge.alurachallenge_literalura;

import com.alurachallenge.alurachallenge_literalura.MainMenu.MainMenu;
import com.alurachallenge.alurachallenge_literalura.Repository.AuthorRepository;
import com.alurachallenge.alurachallenge_literalura.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlurachallengeLiteraluraApplication implements CommandLineRunner {

	private final MainMenu mainMenu;

	@Autowired
	public AlurachallengeLiteraluraApplication(MainMenu mainMenu) {
		this.mainMenu = mainMenu;
	}

	public static void main(String[] args) {
		SpringApplication.run(AlurachallengeLiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		mainMenu.showMenu();
	}
}
