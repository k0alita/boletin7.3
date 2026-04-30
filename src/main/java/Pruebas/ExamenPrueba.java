package Pruebas;

import java.io.IOException;
import java.nio.file.*;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class ExamenPrueba {

    static final String BASE = "src/main/java/Pruebas/";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║       GESTOR DE FICHEROS     ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("║  1. Crear directorio         ║");
            System.out.println("║  2. Crear fichero de texto   ║");
            System.out.println("║  3. Borrar fichero           ║");
            System.out.println("║  4. Listar ficheros          ║");
            System.out.println("║  5. Salir                    ║");
            System.out.println("╚══════════════════════════════╝");
            System.out.print("Elige una opción: ");

            opcion = leerOpcion();

            switch (opcion) {
                case 1 -> crearDirectorio();
                case 2 -> crearFichero();
                case 3 -> borrarFichero();
                case 4 -> listarFicheros();
                case 5 -> System.out.println("Saliendo...");
                default -> System.err.println("Opción no válida. Elige entre 1 y 5.");
            }
        } while (opcion != 5);
    }

    // ── Leer opción del menú de forma segura ──────────────────────────────────
    static int leerOpcion() {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1; // fuerza el default del switch
        }
    }

    // ── Opción 1 ──────────────────────────────────────────────────────────────
    static void crearDirectorio() {
        System.out.print("Nombre del directorio: ");
        Path ruta = Path.of(BASE + sc.nextLine());
        try {
            Files.createDirectory(ruta);
            System.out.println("Directorio creado: " + ruta.toAbsolutePath());
        } catch (FileAlreadyExistsException e) {
            System.err.println("Error: el directorio ya existe.");
        } catch (IOException e) {
            System.err.println("No se pudo crear el directorio: " + e.getMessage());
        }
    }

    // ── Opción 2 ──────────────────────────────────────────────────────────────
    static void crearFichero() {
        System.out.print("Nombre del fichero: ");
        Path ruta = Path.of(BASE + sc.nextLine());
        System.out.print("Contenido: ");
        String contenido = sc.nextLine();
        try {
            Files.writeString(ruta, contenido, StandardOpenOption.CREATE_NEW);
            System.out.println("Fichero creado: " + ruta.toAbsolutePath());
        } catch (FileAlreadyExistsException e) {
            System.err.println("Error: el fichero ya existe.");
        } catch (IOException e) {
            System.err.println("No se pudo crear el fichero: " + e.getMessage());
        }
    }

    // ── Opción 3 ──────────────────────────────────────────────────────────────
    static void borrarFichero() {
        System.out.print("Nombre del fichero a eliminar: ");
        Path ruta = Path.of(BASE + sc.nextLine());
        try {
            Files.delete(ruta);
            System.out.println("Fichero eliminado correctamente.");
        } catch (NoSuchFileException e) {
            System.err.println("Error: el fichero no existe.");
        } catch (DirectoryNotEmptyException e) {
            System.err.println("Error: es un directorio no vacío.");
        } catch (IOException e) {
            System.err.println("No se pudo eliminar: " + e.getMessage());
        }
    }

    // ── Opción 4 ──────────────────────────────────────────────────────────────
    static void listarFicheros() {
        System.out.print("Nombre de la carpeta: ");
        Path ruta = Path.of(BASE + sc.nextLine());
        try (Stream<Path> stream = Files.list(ruta)) {
            List<Path> ficheros = stream
                    .filter(Files::isRegularFile)
                    .sorted(Comparator.comparing(p -> p.getFileName().toString()))
                    .toList();

            if (ficheros.isEmpty()) {
                System.out.println("La carpeta no contiene ficheros.");
                return;
            }
            System.out.println("\nFicheros en: " + ruta.toAbsolutePath());
            System.out.println("─".repeat(40));
            for (Path fichero : ficheros) {
                long sizeKB = Files.size(fichero) / 1024;
                System.out.printf("%-30s %d KB%n", fichero.getFileName(), sizeKB);
            }
        } catch (NotDirectoryException e) {
            System.err.println("Error: la ruta no es una carpeta.");
        } catch (NoSuchFileException e) {
            System.err.println("Error: la carpeta no existe.");
        } catch (IOException e) {
            System.err.println("Error al leer la carpeta: " + e.getMessage());
        }
    }
}