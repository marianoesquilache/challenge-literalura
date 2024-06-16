package com.mariano.literalura.Literalura.main;


import com.mariano.literalura.Literalura.dto.AutorDto;
import com.mariano.literalura.Literalura.dto.BusquedaDto;
import com.mariano.literalura.Literalura.dto.LibroDto;
import com.mariano.literalura.Literalura.model.Autor;
import com.mariano.literalura.Literalura.model.Libro;
import com.mariano.literalura.Literalura.repository.AutorRepository;
import com.mariano.literalura.Literalura.repository.LibroRepository;
import com.mariano.literalura.Literalura.service.AutorService;
import com.mariano.literalura.Literalura.service.ConsumoApi;
import com.mariano.literalura.Literalura.service.ConvierteDatos;
import com.mariano.literalura.Literalura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;


@Component
public class AppMain {

    private final String URL_BASE = "https://gutendex.com/books/";
    private LibroService libroService;
    private AutorService autorService;
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;
    private Scanner entrada;
    private ConsumoApi consumoApi;
    private ConvierteDatos conversor;


    @Autowired
    public AppMain(LibroService libroService, AutorService autorService, LibroRepository libroRepository, AutorRepository autorRepository, Scanner entrada, ConsumoApi consumoApi, ConvierteDatos conversor) {
        this.libroService = libroService;
        this.autorService = autorService;
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
        this.entrada = entrada;
        this.consumoApi = consumoApi;
        this.conversor = conversor;
    }

    public void mostrarMenu() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("""
                    Bienvenido, ingrese una opcion: 
                    1. Buscar un libro por título
                    2. Mostrar libros buscados
                    3. Listar autores de libros buscados
                    4. Mostrar autores de libros buscados vivos en determinado año
                    5. Listar libros por idioma
                    0. Salir                    
                                    """);
            opcion = entrada.nextInt();
            entrada.nextLine();
            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    mostrarLibrosBuscados();
                    break;
                case 3:
                    mostrarAutoresBuscados();
                    break;
                case 4:
                    mostrarAutoresVivosEnDeterminadoAno();
                    break;

                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Gracias , hasta luego!");
                    break;
                default:
                    System.out.println("Opción incorrecta!");
                    break;


            }
        }
    }

    private void listarLibrosPorIdioma() {
        System.out.println("Introduzca idioma");
        var idioma = entrada.nextLine();
        List<LibroDto>librosPorIdioma = libroService.cargarLibrosPorIdioma(idioma);
        var cantLibros = librosPorIdioma.stream()
                .count();

        librosPorIdioma.forEach(System.out::println);
        System.out.println("Cantidad de libros en este idioma: " + cantLibros + " libros.");
    }

    private void mostrarAutoresVivosEnDeterminadoAno() {
        System.out.println("Introduzca un año a buscar");
         var ano = entrada.nextInt();
         List<AutorDto> autoresVivos = autorService.cargarAutoresVivos(ano);
         autoresVivos.forEach(System.out::print);
    }

    private void mostrarLibrosBuscados() {
        List<LibroDto> libros = libroService.cargarLibros();
        libros.forEach(System.out::println);

    }


    private void mostrarAutoresBuscados() {
        List<AutorDto> autores = autorService.cargarAutores();
        autores.forEach(System.out::println
        );
    }


    public void buscarLibroPorTitulo() {
        System.out.println("Introduzca el titulo a buscar:");
        var titulo = entrada.nextLine();
        if (titulo.isEmpty()) {
            System.out.println("Debe introducir un titulo.");
        }
        var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + titulo.replace(" ", "%20"));
        BusquedaDto busqueda = conversor.obtenerDatos(json, BusquedaDto.class);
        Optional<Libro> libroEncontrado = busqueda.libros().stream()
                .map(Libro::new)
                .findFirst();
        if (libroEncontrado.isPresent()) {
            System.out.println("A punto de intentar buscar autor en bd");
            Autor autor = autorRepository.findByNombreContainsIgnoreCase(libroEncontrado.get().getAutor().getNombre());

            if (autor == null) {
                System.out.println("Autor null");
                Autor nuevoAutor = libroEncontrado.get().getAutor();
                System.out.println("Intentando guardar este autor " + nuevoAutor);
                autor = autorRepository.save(nuevoAutor);
            }

            Libro libro = libroEncontrado.get();

            try {
                System.out.println("intentando asignar el autor");
                libro.setAutor(autor);
                System.out.println(libro);
                libroRepository.save(libro);
            } catch (Exception e) {
                System.out.println("Error al guardar el libro" + e.getMessage());
            }
        } else {
            System.out.println("No hemos encontrado este libro");
        }
    }


}

