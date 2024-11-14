package com.alurachallenge.alurachallenge_literalura.Repository;

import com.alurachallenge.alurachallenge_literalura.Models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByName(String name);

    @Query("SELECT a FROM Author a WHERE " +
            "(a.birth_year IS NOT NULL AND a.birth_year <= :year) AND " +
            "(a.death_year IS NULL OR a.death_year > :year)")
    List<Author> findAuthorsAliveInYear(int year);
}
