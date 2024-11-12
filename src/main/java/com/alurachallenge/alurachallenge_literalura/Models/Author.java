package com.alurachallenge.alurachallenge_literalura.Models;

import com.alurachallenge.alurachallenge_literalura.Resouerces.AuthorData;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer birth_year;
    private Integer death_year;
    @ManyToMany(mappedBy = "authors", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Book> books;

    public Author(){}

    public Author(AuthorData authorData){
        this.name = authorData.name();
        this.birth_year = authorData.birth_year();
        this.death_year = authorData.death_year();
    }

    @Override
    public String toString() {
        String bookTitles = books.stream()
                .map(Book::getTitle)
                .collect(Collectors.joining(", "));

        return "------------|AUTOR|------------\n" +
                "Nombre: " + name + "\n" +
                "Año de nacimiento: " + birth_year + "\n" +
                "Año de defunción: " + death_year + "\n" +
                "Libros: "+ books + "\n" +
                "------------------------------"
                ;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(Integer birth_year) {
        this.birth_year = birth_year;
    }

    public Integer getDeath_year() {
        return death_year;
    }

    public void setDeath_year(Integer death_year) {
        this.death_year = death_year;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
        books.forEach(b->b.getAuthors().add(this));
    }
}
