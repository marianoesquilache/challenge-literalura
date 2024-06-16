package com.mariano.literalura.Literalura.model;

import com.mariano.literalura.Literalura.dto.AutorDto;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Integer anoNac;
    private Integer anoMue;
    @OneToMany(mappedBy = "autor")
    private List<Libro> libros;

    public Autor() {
    }


    public Autor(AutorDto autorDto) {
        this.nombre = autorDto.name();
        this.anoNac = autorDto.anoNac();
        this.anoMue = autorDto.anoMue();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAnoNac() {
        return anoNac;
    }

    public void setAnoNac(Integer anoNac) {
        this.anoNac = anoNac;
    }

    public Integer getAnoMue() {
        return anoMue;
    }

    public void setAnoMue(Integer anoMue) {
        this.anoMue = anoMue;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return "Autor:\n" +
                "id =" + id +
                ", nombre='" + nombre + '\'' +
                ", anoNac=" + anoNac +
                ", anoMue=" + anoMue;
    }
}
