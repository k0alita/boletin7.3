package Pruebas;

import RepasoExamen.Matricula2;
import RepasoExamen.Matricula3;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PosibleExamen2 {
    public static void main(String[] args) {

        Path ruta = Path.of("src\\main\\java\\Pruebas\\logs_servidor.txt");

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
        }

        Pattern pattern = Pattern.compile("^(?<tipo>ERROR|INFO)-\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\s[a-z0-9]{3,10}$");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (Stream<String> lineas = Files.lines(ruta)) {
            List<Logs> erroresEncontrados = lineas.map(pattern::matcher)
                    .filter(Matcher::matches).filter(matcher -> matcher.group("tipo").equalsIgnoreCase("ERROR"))
                    .map(m -> new Logs(m.group()))
                    .toList();
            if (!erroresEncontrados.isEmpty()) {
                Path archivoJson = pathCarpeta.resolve("errores.json");
                String jsonSalida = gson.toJson(erroresEncontrados);

                Files.writeString(archivoJson, jsonSalida);
                System.out.println("Se han guardado " + erroresEncontrados.size() + " errores en formato JSON.");

            } else {
                System.out.println("No se encontraron errores válidos para guardar.");
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try (Stream<String> lineas = Files.lines(ruta)){
            List<Logs> infoEncontrados = lineas.map(pattern::matcher)
                    .filter(Matcher::matches).filter(matcher -> matcher.group("tipo").equalsIgnoreCase("INFO"))
                    .map(m -> new Logs(m.group()))
                    .toList();
            if (!infoEncontrados.isEmpty()) {
                Path archivoJson = pathCarpeta.resolve("info.json");
                String jsonSalida = gson.toJson(infoEncontrados);

                Files.writeString(archivoJson, jsonSalida);
                System.out.println("Se han guardado " + infoEncontrados.size() + " info en formato JSON.");

            } else {
                System.out.println("No se encontraron info válidos para guardar.");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    record Logs(String tipo) {

    }
}