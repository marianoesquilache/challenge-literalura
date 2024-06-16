package com.mariano.literalura.Literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LibroDto(
        Long id,
        @JsonAlias("title")
        String titulo,
        @JsonAlias("authors")
        List<AutorDto> autores,

        @JsonAlias("languages")
        List<String>idioma,

        @JsonAlias("download_count")
        Double descargas
        ){

    @Override
    public String toString() {
        String autoresNombres = autores.stream()
                .map(AutorDto::name)
                .collect(Collectors.joining(", "));

        return
                "\t\nid=" + id +
                "\t\nTítulo = " + titulo +
                "\t\nIdioma = " + idioma +
                "\t\nAutor = " + autores.get(0).name() +
                "\t\nDescargas= " + descargas+ "\n";
    }
}