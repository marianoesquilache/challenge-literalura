package com.mariano.literalura.Literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BusquedaDto(@JsonAlias("results")List<LibroDto>libros) {

}
