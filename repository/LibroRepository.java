package com.mariano.literalura.Literalura.repository;

import com.mariano.literalura.Literalura.dto.LibroDto;
import com.mariano.literalura.Literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByIdioma(String idioma);
}
