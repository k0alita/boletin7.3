package Pruebas;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExamenCorregido {
    public static void main(String[] args) {
        Path ruta = Path.of("src/main/java/Pruebas/logs_servidor.txt");

        if (!Files.exists(ruta)) {
            System.out.println("No existe el archivo especificado");
            return; // Termina la ejecución si no hay log
        } else {
            System.out.println("Archivo log encontrado. ¡Goty!");
        }

        String nombreDirectorio = "Registros_Clasificados";
        Path pathDirectorio = Paths.get("src/main/java/Pruebas/" + nombreDirectorio);
        try {
            Files.createDirectories(pathDirectorio);
            System.out.println("Directorio listo para usar");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        Pattern patron = Pattern.compile("^(ERROR|INFO)-\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\s[a-z0-9]{3,10}$");

        Path rutasErrores = pathDirectorio.resolve("errores.txt");
        Path rutaInfo = pathDirectorio.resolve("info.txt");

        int contErrores = 0;
        int contInfo = 0;

        try {
            List<String> lineas = Files.readAllLines(ruta);

            for (String linea : lineas) {
                Matcher matcher = patron.matcher(linea);

                if (matcher.matches()) {
                    if (linea.startsWith("ERROR")) {
                        Files.writeString(rutasErrores, linea + System.lineSeparator(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                        contErrores++;
                    } else if (linea.startsWith("INFO")) {
                        Files.writeString(rutaInfo, linea + System.lineSeparator(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                        contInfo++;
                    }
                } else {
                    System.out.println("Ignorada por formato: " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
