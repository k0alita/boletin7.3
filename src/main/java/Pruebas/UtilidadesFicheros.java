package Pruebas;

import java.nio.file.*;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class UtilidadesFicheros {

    // 1. Leer todas las líneas a una Lista (Para procesar línea a línea)
    public static List<String> leerFichero(String ruta) {
        try {
            return Files.readAllLines(Paths.get(ruta));
        } catch (IOException e) {
            System.out.println("Error al leer: " + e.getMessage());
            return new ArrayList<>(); // Devuelve lista vacía si falla
        }
    }

    // 2. Escribir una Lista de String sobrescribiendo el fichero
    public static void escribirFichero(String ruta, List<String> lineas) {
        try {
            Files.write(Paths.get(ruta), lineas);
            System.out.println("Archivo guardado con éxito.");
        } catch (IOException e) {
            System.out.println("Error al escribir: " + e.getMessage());
        }
    }

    // 3. Añadir texto al final de un fichero (APPEND) - ¡Muy de examen!
    public static void anadirAFichero(String ruta, String lineaNueva) {
        try {
            // Se le añade un salto de línea al final
            Files.writeString(Paths.get(ruta), lineaNueva + System.lineSeparator(),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Error al añadir: " + e.getMessage());
        }
    }

    // 4. Listar contenido de un directorio (Solo 1 nivel)
    public static void listarDirectorio(String rutaCarpeta) {
        Path directorio = Paths.get(rutaCarpeta);
        if (Files.exists(directorio) && Files.isDirectory(directorio)) {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(directorio)) {
                for (Path archivo : stream) {
                    // Imprime nombre y peso en KB
                    System.out.println(archivo.getFileName() + " - " +
                            (Files.size(archivo) / 1024) + " KB");
                }
            } catch (IOException e) {
                System.out.println("Error al listar: " + e.getMessage());
            }
        }
    }

    // 5. Borrar fichero/directorio de forma segura
    public static boolean borrarFichero(String ruta) {
        try {
            return Files.deleteIfExists(Paths.get(ruta));
        } catch (IOException e) {
            System.out.println("El directorio no está vacío o está en uso.");
            return false;
        }
    }

    public static void validarFicheroPersonas(String rutaEntrada) {
        List<String> lineas = leerFichero(rutaEntrada);
        // Regex: 3 palabras de al menos 2 letras + espacio + número del 1 al 99
        Pattern patron = Pattern.compile("^[a-zA-Z]{2,} [a-zA-Z]{2,} [a-zA-Z]{2,} (?:[1-9][0-9]?)$");

        for (int i = 0; i < lineas.size(); i++) {
            String linea = lineas.get(i);
            if (patron.matcher(linea).matches()) {
                System.out.println("Línea " + (i+1) + " CORRECTA: " + linea);
                // Podrías usar anadirAFichero("correctos.txt", linea) aquí
            } else {
                System.out.println("Línea " + (i+1) + " INCORRECTA: " + linea);
            }
        }
    }
}
