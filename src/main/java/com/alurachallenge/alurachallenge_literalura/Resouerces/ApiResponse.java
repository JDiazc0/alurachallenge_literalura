package com.alurachallenge.alurachallenge_literalura.Resouerces;

import com.alurachallenge.alurachallenge_literalura.Models.Book;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ApiResponse (
        @JsonAlias("results") List<BookData> booksList
){
}
