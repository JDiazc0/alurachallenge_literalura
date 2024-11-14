package com.alurachallenge.alurachallenge_literalura.Resouerces;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ApiResponse (
        @JsonAlias("results") List<BookDTO> booksList
){
}
