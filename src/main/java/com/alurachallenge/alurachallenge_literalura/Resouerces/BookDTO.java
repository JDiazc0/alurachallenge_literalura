package com.alurachallenge.alurachallenge_literalura.Resouerces;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Set;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookDTO(
        @JsonAlias("title") String title,
        @JsonAlias("authors") Set<AuthorDTO> authors,
        @JsonAlias("languages") List<String> languages,
        @JsonAlias("download_count") Integer download_count
) {
}
