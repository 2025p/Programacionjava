/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.actividad12;

/**
 *
 * @author USER
 */
public class Actividad12 {

    public static void main(String[] args) {
     Biblioteca biblioteca = new Biblioteca();

        System.out.println("--- Inicialización y Registro de Usuarios ---");
        biblioteca.registrarUsuario(new Usuario("U1", "Carlos"));
        biblioteca.registrarUsuario(new Usuario("U2", "Samy"));
        System.out.println();

        System.out.println("--- Añadir Libros ---");
        biblioteca.anadirLibro(new Libro("ISBN-001", "Clean Code", "Robert C. Martin", "Software"));
        biblioteca.anadirLibro(new Libro("ISBN-002", "Effective Java", "Joshua Bloch", "Java"));
        System.out.println();

        System.out.println("--- Préstamo de Libros ---");
        biblioteca.prestarLibro("U1", "ISBN-001");
        biblioteca.prestarLibro("U2", "ISBN-002");
        System.out.println();

        System.out.println("--- Listar Libros Prestados por Carlos (U1) ---");
        System.out.println(biblioteca.listarPrestados("U1"));
        System.out.println();

        System.out.println("--- Devolución de Libro ---");
        biblioteca.devolverLibro("U1", "ISBN-001");
        System.out.println();

        System.out.println("--- Búsqueda de Libros por Autor ---");
        System.out.println("Resultados para 'Bloch': " + biblioteca.buscarPorAutor("Bloch"));
        System.out.println();

        System.out.println("--- Búsqueda de Libros por Categoría ---");
        System.out.println("Resultados para 'Software': " + biblioteca.buscarPorCategoria("Software"));
        System.out.println();
    }
}

