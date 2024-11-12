package com.alurachallenge.alurachallenge_literalura.Models;

import com.alurachallenge.alurachallenge_literalura.Resouerces.BookData;
import jakarta.persistence.*;

import java.util.List;
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
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "authors_books",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors;

    public Book(){}

    public Book(BookData bookData){
        this.title = bookData.title();
        this.authors = bookData.authors().stream()
                .map(Author::new)
                .collect(Collectors.toList());
        this.language = bookData.languages().isEmpty() ? null : Language.fromCode(bookData.languages().get(0));
        this.download_count = bookData.download_count();
    }

    @Override
    public String toString() {
        String authorNames = authors.stream()
                .map(Author::getName)
                .collect(Collectors.joining(", "));

        return "------------|LIBRO|------------\n" +
                "Titulo: " + title + "\n" +
                "Idioma(s): " + language  + "\n" +
                "Número de descargas:" + download_count +  "\n" +
                "Autores: " + authorNames + "\n" +
                "------------------------------";
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

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
        authors.forEach(a->a.getBooks().add(this));
    }
}
