package com.alurachallenge.alurachallenge_literalura.MainMenu;

import com.alurachallenge.alurachallenge_literalura.Exceptions.CustomExceptions;
import com.alurachallenge.alurachallenge_literalura.Models.Author;
import com.alurachallenge.alurachallenge_literalura.Models.Book;
import com.alurachallenge.alurachallenge_literalura.Models.Language;
import com.alurachallenge.alurachallenge_literalura.Repository.AuthorRepository;
import com.alurachallenge.alurachallenge_literalura.Repository.BookRepository;
import com.alurachallenge.alurachallenge_literalura.Service.BookManagmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
public class MainMenu {
    private Scanner scanner = new Scanner(System.in);
    private BookManagmentService bookManagmentService;
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    @Autowired
    public MainMenu(BookManagmentService bookManagmentService,
                    BookRepository bookRepository,
                    AuthorRepository authorRepository){
        this.bookManagmentService = bookManagmentService;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public void showMenu(){
        boolean isWorking = true;
        while (isWorking){
            displayMenu();
            String selectedOption = scanner.nextLine();
            try {
                switch (selectedOption){
                    case "1":
                        searchAndSaveBook();
                        break;
                    case "2":
                        showBDBooks();
                        break;
                    case "3":
                        showBDAuthors();
                        break;
                    case "4":
                        showAuthorsAlive();
                        break;
                    case "5":
                        filterBooksByLanguage();
                        break;
                    case "0":
                        isWorking = false;
                        return;
                    default:
                        System.out.println("La opción ingresada no es valida");
                }
            } catch (Exception e){
                System.out.println("Error: "+ e.getMessage());
            }
        }
    }

    /**
     * Display menu on terminal
     */
    private void displayMenu(){
        System.out.println("""
                    
                    |--------------------|Bienvenido|--------------------|
                    |--------------------|Literalura|--------------------|
                    1. Registrar nuevo libro en la base de datos.
                    2. Ver libros guardados.
                    3. Ver autores guardados.
                    4. Ver autores vivos en un determinado año.
                    5. Filtrar libros guardados por su idioma.
                    
                    0. Cerrar Literalura.
                    |----------------------------------------------------|
                    """);
    }

    /**
     * Inserts a book not existing in the database
     * @return void
     */
    private void searchAndSaveBook() {
        System.out.println("Ingrese el nombre del libro que desea agregar:");
        var bookName = scanner.nextLine();

        try {
            bookManagmentService.searchAndSaveBook(bookName);
            System.out.println("El libro guardado en base de datos es:\n" + bookName);
        } catch (CustomExceptions.DuplicateBookException e){
            System.out.println("El libro ya existe en la base de datos");
        }catch (CustomExceptions.BookNotFoundException e){
            System.out.println("No se encontró el libro en la API");
        }catch (Exception e){
            System.out.println("Error al guardar el libro: " + e.getMessage());
        }
    }

    /**
     * Show books in database
     * @return books array
     */
    private void showBDBooks(){
        List<Book> books = bookManagmentService.getAllBooks();
        if (books.isEmpty()){
            System.out.println("No hay libros guardados en la base de datos :(");
            return;
        }
        books.forEach(System.out::println);
    }

    /**
     * Show authors in database
     * @return authors array
     */
    private void showBDAuthors(){
        List<Author> authors = bookManagmentService.getAllAuthors();
        if (authors.isEmpty()){
            System.out.println("No hay autores guardados en la base de datos :(");
            return;
        }
        authors.forEach(System.out::println);
    }

    /**
     * Show authors alive in time range
     * @return authors array
     */
    private void showAuthorsAlive(){
        System.out.println("Ingrese el año en que desea buscar: ");
        try {
            int year = Integer.parseInt(scanner.nextLine());
            List<Author> authors = bookManagmentService.getAuthorsAliveInYear(year);

            if (authors.isEmpty()) {
                System.out.println("No se encontraron autores vivos en el año " + year);
                return;
            }

            authors.forEach(System.out::println);
        } catch (NumberFormatException e) {
        System.out.println("Por favor ingrese un año válido");
        }
    }

    private void filterBooksByLanguage() {
        System.out.println("Idiomas disponibles: " +
                Arrays.toString(Language.values()));
        System.out.println("Ingrese el idioma (ejemplo: ES, EN, FR):");

        try {
            Language language = Language.valueOf(scanner.nextLine().toUpperCase());
            List<Book> books = bookManagmentService.getBooksByLanguage(language);

            if (books.isEmpty()) {
                System.out.println("No hay libros en el idioma " + language);
                return;
            }

            books.forEach(System.out::println);
        } catch (IllegalArgumentException e) {
            System.out.println("Idioma no válido");
        }
    }
}
