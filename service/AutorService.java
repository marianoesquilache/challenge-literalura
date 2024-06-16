package com.mariano.literalura.Literalura.service;

import com.mariano.literalura.Literalura.dto.AutorDto;
import com.mariano.literalura.Literalura.model.Autor;
import com.mariano.literalura.Literalura.repository.AutorRepository;
import com.mariano.literalura.Literalura.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutorService {
    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    public AutorService(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public List<AutorDto>cargarAutores(){
        return convertirAutores(autorRepository.findAll());
    }

    public List<AutorDto> cargarAutoresVivos(int ano) {
        return convertirAutores(autorRepository.findByAnoNacLessThanAndAnoMueGreaterThan(ano,ano));
    }

    public List <AutorDto>convertirAutores(List<Autor> aut){
        return aut.stream()
                .map(a->new AutorDto(a.getId(),a.getNombre(),a.getAnoNac(),a.getAnoMue()))
                .collect(Collectors.toList());
    }



}
