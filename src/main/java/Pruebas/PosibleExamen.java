package Pruebas;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PosibleExamen {
    public static void main(String[] args) {

        Path ruta = Path.of("src/main/java/Pruebas/logs_servidor.txt");

        if (!Files.exists(ruta)) {
            System.out.println("No existe el archivo especificado");
            return;
        } else {
            System.out.println("goty");
        }

        String nombreDirectorio = "Registros_Clasificados";
        Path pathCarpeta = Paths.get("src/main/java/Pruebas/" + nombreDirectorio);
        try {
            Files.createDirectories(pathCarpeta);
            System.out.println("Directorio " + nombreDirectorio + " listo para usar.");
        } catch (IOException e) {
            System.err.println("No se pudo crear el directorio: " + e.getMessage());
            return;
        }

        Pattern pattern = Pattern.compile("^(?<tipo>ERROR|INFO)-\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\s[a-z0-9]{3,10}$");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // SE LEE EL ARCHIVO UNA SOLA VEZ
        try (Stream<String> lineas = Files.lines(ruta)) {

            // Agrupamos los resultados en un Map.
            // La clave (Key) será el tipo (ej: "ERROR" o "INFO").
            // El valor (Value) será la Lista de Logs correspondiente a esa clave.
            Map<String, List<Logs>> registrosClasificados = lineas
                    .map(pattern::matcher)
                    .filter(Matcher::matches)
                    .collect(Collectors.groupingBy(
                            m -> m.group("tipo").toUpperCase(), // Usamos el tipo como clave de agrupación
                            Collectors.mapping(m -> new Logs(m.group()), Collectors.toList()) // Creamos los Records
                    ));

            // Extraemos las listas del Map
            // getOrDefault() devuelve una lista vacía si no encontró ninguno de ese tipo
            List<Logs> erroresEncontrados = registrosClasificados.getOrDefault("ERROR", List.of());
            List<Logs> infoEncontrados = registrosClasificados.getOrDefault("INFO", List.of());

            // 1. Guardar ERRORES
            if (!erroresEncontrados.isEmpty()) {
                Path archivoErrores = pathCarpeta.resolve("errores.json");
                Files.writeString(archivoErrores, gson.toJson(erroresEncontrados));
                System.out.println("Se han guardado " + erroresEncontrados.size() + " errores en formato JSON.");
            } else {
                System.out.println("No se encontraron errores válidos para guardar.");
            }

            // 2. Guardar INFO
            if (!infoEncontrados.isEmpty()) {
                Path archivoInfo = pathCarpeta.resolve("info.json");
                Files.writeString(archivoInfo, gson.toJson(infoEncontrados));
                System.out.println("Se han guardado " + infoEncontrados.size() + " info en formato JSON.");
            } else {
                System.out.println("No se encontraron info válidos para guardar.");
            }

        } catch (IOException e) {
            System.out.println("Error procesando el archivo: " + e.getMessage());
        }
    }

    record Logs(String lineaCompleta) {
    }
}