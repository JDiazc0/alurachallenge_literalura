package com.alurachallenge.alurachallenge_literalura.Models;

import com.alurachallenge.alurachallenge_literalura.Resouerces.AuthorDTO;
import jakarta.persistence.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String name;
    private Integer birth_year;
    private Integer death_year;
    @ManyToMany(mappedBy = "authors", fetch = FetchType.EAGER)
    private Set<Book> books = new HashSet<>();

    public Author(){}

    public Author(AuthorDTO authorDTO){
        this.name = authorDTO.name();
        this.birth_year = authorDTO.birth_year();
        this.death_year = authorDTO.death_year();
    }

    @Override
    public String toString() {
        String bookTitles = books.stream()
                .map(Book::getTitle)
                .collect(Collectors.joining(", "));

        return "\n|------------|AUTOR|------------|\n" +
                "Nombre: " + name + "\n" +
                "Año de nacimiento: " + birth_year + "\n" +
                "Año de defunción: " + death_year + "\n" +
                "Libros: "+ bookTitles + "\n" +
                "|-------------------------------|\n"
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

    public Set<Book> getBooks() {
        return books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author author)) return false;
        return name != null && name.equals(author.getName());
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
