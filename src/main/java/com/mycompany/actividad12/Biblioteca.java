/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.actividad12;

import java.util.*;
import java.util.stream.Collectors;

public class Biblioteca {
    
     private final Map<String, Libro> catalogoPorIsbn;
    private final Map<String, Usuario> usuariosPorId;
    private final Set<String> isbnsPrestados;

    public Biblioteca() {
        this.catalogoPorIsbn = new HashMap<>();
        this.usuariosPorId = new HashMap<>();
        this.isbnsPrestados = new HashSet<>();
    }

    // Funcionalidades de Libros
    public boolean anadirLibro(Libro libro) {
        if (catalogoPorIsbn.containsKey(libro.getIsbn())) {
            System.out.println("Error: El libro con ISBN " + libro.getIsbn() + " ya existe en el catálogo.");
            return false;
        }
        catalogoPorIsbn.put(libro.getIsbn(), libro);
        System.out.println("Libro añadido: " + libro.getTitulo());
        return true;
    }

    public boolean quitarLibro(String isbn) {
        if (isbnsPrestados.contains(isbn)) {
            System.out.println("Error: No se puede quitar el libro con ISBN " + isbn + " porque está prestado.");
            return false;
        }
        Libro libroRemovido = catalogoPorIsbn.remove(isbn);
        if (libroRemovido != null) {
            System.out.println("Libro quitado: " + libroRemovido.getTitulo());
            return true;
        }
        System.out.println("Error: El libro con ISBN " + isbn + " no se encontró en el catálogo.");
        return false;
    }

    // Funcionalidades de Usuarios
    public boolean registrarUsuario(Usuario usuario) {
        if (usuariosPorId.containsKey(usuario.getId())) {
            System.out.println("Error: El usuario con ID " + usuario.getId() + " ya está registrado.");
            return false;
        }
        usuariosPorId.put(usuario.getId(), usuario);
        System.out.println("Usuario registrado: " + usuario.getNombre());
        return true;
    }

    public boolean darBajaUsuario(String id) {
        Usuario usuario = usuariosPorId.get(id);
        if (usuario == null) {
            System.out.println("Error: El usuario con ID " + id + " no se encontró.");
            return false;
        }
        if (!usuario.getIsbnsPrestados().isEmpty()) {
            System.out.println("Error: El usuario " + usuario.getNombre() + " aún tiene libros prestados.");
            return false;
        }
        usuariosPorId.remove(id);
        System.out.println("Usuario dado de baja: " + usuario.getNombre());
        return true;
    }

    // Funcionalidades de Préstamos
    public boolean prestarLibro(String idUsuario, String isbn) {
        if (!catalogoPorIsbn.containsKey(isbn)) {
            System.out.println("Error: El libro con ISBN " + isbn + " no existe en el catálogo.");
            return false;
        }
        if (isbnsPrestados.contains(isbn)) {
            System.out.println("Error: El libro con ISBN " + isbn + " ya está prestado.");
            return false;
        }
        Usuario usuario = usuariosPorId.get(idUsuario);
        if (usuario == null) {
            System.out.println("Error: El usuario con ID " + idUsuario + " no existe.");
            return false;
        }
        usuario.getIsbnsPrestados().add(isbn);
        isbnsPrestados.add(isbn);
        System.out.println("Libro prestado: " + catalogoPorIsbn.get(isbn).getTitulo() + " a " + usuario.getNombre());
        return true;
    }

    public boolean devolverLibro(String idUsuario, String isbn) {
        Usuario usuario = usuariosPorId.get(idUsuario);
        if (usuario == null) {
            System.out.println("Error: El usuario con ID " + idUsuario + " no existe.");
            return false;
        }
        if (!usuario.getIsbnsPrestados().contains(isbn)) {
            System.out.println("Error: El usuario " + usuario.getNombre() + " no tiene prestado el libro con ISBN " + isbn);
            return false;
        }
        usuario.getIsbnsPrestados().remove(isbn);
        isbnsPrestados.remove(isbn);
        System.out.println("Libro devuelto: " + catalogoPorIsbn.get(isbn).getTitulo() + " por " + usuario.getNombre());
        return true;
    }

    // Funcionalidades de Búsqueda
    public List<Libro> buscarPorTitulo(String texto) {
        return catalogoPorIsbn.values().stream()
                .filter(libro -> libro.getTitulo().toLowerCase().contains(texto.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Libro> buscarPorAutor(String texto) {
        return catalogoPorIsbn.values().stream()
                .filter(libro -> libro.getAutor().toLowerCase().contains(texto.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Libro> buscarPorCategoria(String texto) {
        return catalogoPorIsbn.values().stream()
                .filter(libro -> libro.getCategoria().toLowerCase().contains(texto.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Libro> listarPrestados(String idUsuario) {
        Usuario usuario = usuariosPorId.get(idUsuario);
        if (usuario == null) {
            System.out.println("Error: Usuario no encontrado.");
            return Collections.emptyList();
        }
        return usuario.getIsbnsPrestados().stream()
                .map(catalogoPorIsbn::get)
                .filter(Objects::nonNull) // Filtra en caso de que un ISBN no exista en el catálogo (ej. eliminado)
                .collect(Collectors.toList());
    }
}
    

