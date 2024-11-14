package com.alurachallenge.alurachallenge_literalura.Repository;

import com.alurachallenge.alurachallenge_literalura.Models.Book;
import com.alurachallenge.alurachallenge_literalura.Models.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    boolean existsByTitle(String title);

    List<Book> findByLanguage(Language language);
}
