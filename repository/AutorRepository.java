package com.mariano.literalura.Literalura.repository;

import com.mariano.literalura.Literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor,Long> {
    Autor findByNombreContainsIgnoreCase(String nombre);

    List<Autor> findByAnoNacLessThanAndAnoMueGreaterThan(int ano,int ano2);
}
