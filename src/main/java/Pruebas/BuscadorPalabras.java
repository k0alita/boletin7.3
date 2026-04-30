package Pruebas;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class BuscadorPalabras {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // ── 1. Leer fichero y contar palabras ─────────────────────────────────
        System.out.print("Nombre del fichero: ");
        String nombreFichero = sc.nextLine().trim();
        Path rutaFichero = Path.of("src/main/java/Pruebas/" + nombreFichero);

        List<String> lineas;
        try {
            lineas = Files.readAllLines(rutaFichero);
        } catch (NoSuchFileException e) {
            System.err.println("Error: el fichero no existe.");
            return;
        } catch (IOException e) {
            System.err.println("Error al leer el fichero: " + e.getMessage());
            return;
        }

        long totalPalabras = lineas.stream()
                .flatMap(linea -> Arrays.stream(linea.trim().split("\\s+")))
                .filter(p -> !p.isEmpty())
                .count();

        System.out.println("Total de palabras: " + totalPalabras);

        // ── 2. Pedir palabra a buscar ─────────────────────────────────────────
        System.out.print("Palabra a buscar: ");
        String palabraBuscada = sc.nextLine().trim();

        // Buscar líneas que contienen la palabra (ignorando mayúsculas)
        Map<Integer, String> lineasEncontradas = new LinkedHashMap<>();
        for (int i = 0; i < lineas.size(); i++) {
            String linea = lineas.get(i);
            // Comprobamos palabras completas, insensible a mayúsculas
            if (linea.toLowerCase().matches(".*\\b" + palabraBuscada.toLowerCase() + "\\b.*")) {
                lineasEncontradas.put(i + 1, linea);
            }
        }

        // ── 3. Resultado ──────────────────────────────────────────────────────
        if (lineasEncontradas.isEmpty()) {
            System.out.println("La palabra \"" + palabraBuscada + "\" no se encontró en el fichero.");
            return;
        }

        System.out.println("Palabra encontrada en " + lineasEncontradas.size() + " línea(s).");
        escribirFichero(palabraBuscada, nombreFichero, lineasEncontradas, totalPalabras);
    }

    // ── Genera BuscandoPalabra<PALABRA>.txt ───────────────────────────────────
    static void escribirFichero(String palabra, String ficheroOrigen,
                                Map<Integer, String> lineasEncontradas, long totalPalabras) {

        String nombreSalida = "BuscandoPalabra" + palabra + ".txt";
        Path rutaSalida = Path.of(nombreSalida);

        StringBuilder sb = new StringBuilder();
        sb.append("Fichero analizado : ").append(ficheroOrigen).append("\n");
        sb.append("Palabra buscada   : ").append(palabra).append("\n");
        sb.append("Total palabras    : ").append(totalPalabras).append("\n");
        sb.append("Apariciones       : ").append(lineasEncontradas.size()).append("\n");
        sb.append("─".repeat(40)).append("\n");

        for (Map.Entry<Integer, String> entry : lineasEncontradas.entrySet()) {
            sb.append(String.format("Línea %3d: %s%n", entry.getKey(), entry.getValue()));
        }

        try {
            Files.writeString(rutaSalida, sb.toString(), StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Fichero generado: " + rutaSalida.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error al escribir el fichero de salida: " + e.getMessage());
        }
    }
}