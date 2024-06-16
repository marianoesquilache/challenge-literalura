package com.mariano.literalura.Literalura.model;

import com.mariano.literalura.Literalura.dto.LibroDto;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;

    private String idioma;

    @ManyToOne
    @JoinColumn(name="autor_id", nullable = false)
    private Autor autor;
    private Double descargas;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Double getDescargas() {
        return descargas;
    }
    public void setDescargas(Double descargas) {
        this.descargas = descargas;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Libro() {
    }

    public Libro(LibroDto libroDto) {
        this.titulo = libroDto.titulo();
        this.idioma = libroDto.idioma().get(0);
        this.autor = new Autor(libroDto.autores().get(0));
        this.descargas = libroDto.descargas();
    }

    @Override
    public String toString() {
        return "Libro" + id +
                "\t\nid=" + id +
                "\t\nTítulo = " + titulo +
                "\t\nIdioma = " + idioma +
                "\t\nAutor = " + autor +
                "\t\nDescargas= " + descargas+ "\n";
    }
}
