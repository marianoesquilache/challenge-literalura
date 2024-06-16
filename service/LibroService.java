package com.mariano.literalura.Literalura.service;

import com.mariano.literalura.Literalura.dto.AutorDto;
import com.mariano.literalura.Literalura.dto.LibroDto;
import com.mariano.literalura.Literalura.model.Autor;
import com.mariano.literalura.Literalura.model.Libro;
import com.mariano.literalura.Literalura.repository.AutorRepository;
import com.mariano.literalura.Literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibroService {

    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    @Autowired
    public LibroService(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

// Métodos de servicio que usan los repositorios

    public List<LibroDto>cargarLibros(){
        return convertirLibros(libroRepository.findAll());
    }
    public List<LibroDto>convertirLibros(List<Libro> lib){
        return lib.stream()
                .map(l->new LibroDto(l.getId(),l.getTitulo(),
                        List.of(new AutorDto(l.getAutor().getId(),l.getAutor().getNombre(),
                                l.getAutor().getAnoNac(),
                                l.getAutor().getAnoMue())),
                                List.of(l.getIdioma()),
                                l.getDescargas()))
                .collect(Collectors.toList());
    }


    public List<LibroDto> cargarLibrosPorIdioma(String idioma) {
        return convertirLibros(libroRepository.findByIdioma(idioma));

    }
}
