package Pruebas;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejercicio2Examen {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("^([A-Za-z]{2,})\\s([A-Za-z]{2,})\\s([A-Za-z]{2,})\\s([1-9][0-9]?)$");


        Scanner sc = new Scanner(System.in);
        System.out.println("Nombre del fichero: ");
        String nombreFichero = sc.nextLine();

        Path ruta = Path.of("src/main/java/Pruebas/" + nombreFichero);

        List<String> lineas;
        try {
            lineas = Files.readAllLines(ruta);
        } catch (NoSuchFileException e) {
            System.err.println("Error: el fichero no existe.");
            return;
        } catch (IOException e) {
            System.err.println("Error al leer el fichero: " + e.getMessage());
            return;
        }

        if (lineas.isEmpty()) {
            System.out.println("El fichero esta vacio");
            return;
        }

        for (int i = 0; i < lineas.size(); i++) {
            String linea = lineas.get(i).trim();

            if (lineas.isEmpty()) continue;

            Matcher matcher = pattern.matcher(linea);
            if (!matcher.matches()) {
                System.err.println("Fichero NO válido.");
                System.err.printf("Error en línea %d: \"%s\"%n", i + 1, linea);
                return;
            }
        }
        long lineasValidas = lineas.stream()
                .filter(l -> !l.trim().isEmpty())
                .count();

        System.out.println("Fichero válido ✓");
        System.out.println("Número de líneas: " + lineasValidas);
    }
}
