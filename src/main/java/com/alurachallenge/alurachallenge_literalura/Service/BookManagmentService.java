package com.alurachallenge.alurachallenge_literalura.Service;

import com.alurachallenge.alurachallenge_literalura.Exceptions.CustomExceptions;
import com.alurachallenge.alurachallenge_literalura.Models.Author;
import com.alurachallenge.alurachallenge_literalura.Models.Book;
import com.alurachallenge.alurachallenge_literalura.Models.Language;
import com.alurachallenge.alurachallenge_literalura.Repository.AuthorRepository;
import com.alurachallenge.alurachallenge_literalura.Repository.BookRepository;
import com.alurachallenge.alurachallenge_literalura.Resouerces.ApiResponse;
import com.alurachallenge.alurachallenge_literalura.Resouerces.AuthorDTO;
import com.alurachallenge.alurachallenge_literalura.Resouerces.BookDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookManagmentService {
    private FetchAPI fetchAPI;
    private IConvertData convertData;
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private final String BASE_URL = "https://gutendex.com/books/";

    @Autowired
    public BookManagmentService(BookRepository bookRepository, AuthorRepository authorRepository){
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.fetchAPI = new FetchAPI();
        this.convertData = new ConvertData();
    }

    @Transactional
    public void searchAndSaveBook(String bookName){
        var json = fetchAPI.getData(BASE_URL + "?search=" + bookName.replace(" ", "%20"));
        ApiResponse res = convertData.getData(json, ApiResponse.class);

        if (res.booksList().isEmpty()) {
            throw new RuntimeException("No se encontraron libros en el API");
        }

        BookDTO bookDTO = res.booksList().get(0);

        if (bookRepository.existsByTitle(bookDTO.title())) {
            throw new RuntimeException("El libro ya existe en la base de datos");
        }

        Book book = new Book(bookDTO);

        for (AuthorDTO authorDTO : bookDTO.authors()) {
            Author author = authorRepository.findByName(authorDTO.name())
                    .orElseGet(() -> {
                        Author newAuthor = new Author(authorDTO);
                        return authorRepository.save(newAuthor);
                    });

            book.addAuthor(author);
        }
        bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    public List<Author> getAuthorsAliveInYear(int year){
        return authorRepository.findAuthorsAliveInYear(year);
    }

    public List<Book> getBooksByLanguage(Language language){
        return bookRepository.findByLanguage(language);
    }

}

