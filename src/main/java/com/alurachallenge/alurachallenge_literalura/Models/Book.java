package com.alurachallenge.alurachallenge_literalura.Models;

import com.alurachallenge.alurachallenge_literalura.Resouerces.BookDTO;
import jakarta.persistence.*;


import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String title;
    @Enumerated(EnumType.STRING)
    private Language language;
    private Integer download_count;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors = new HashSet<>();

    public Book(){}

    public Book(BookDTO bookDTO){
        this.title = bookDTO.title();
        this.language = Language.fromCode(bookDTO.languages().get(0));
        this.download_count = bookDTO.download_count();
    }

    @Override
    public String toString() {
        String authorNames = authors.stream()
                .map(Author::getName)
                .collect(Collectors.joining(", "));

        return "\n|------------|LIBRO|------------|\n" +
                "Titulo: " + title + "\n" +
                "Idioma(s): " + language  + "\n" +
                "Número de descargas:" + download_count +  "\n" +
                "Autores: " + authorNames + "\n" +
                "|-------------------------------|\n";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Integer getDownload_count() {
        return download_count;
    }

    public void setDownload_count(Integer download_count) {
        this.download_count = download_count;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void addAuthor(Author author){
        authors.add(author);
        author.getBooks().add(this);
    }

    public void removeAuthor(Author author){
        authors.remove(author);
        author.getBooks().remove(this);
    }

    public void setAuthors(Set<Author> authors) {
        this.authors.clear();
        if (authors != null){
            authors.forEach(this::addAuthor);
        }
    }
}
