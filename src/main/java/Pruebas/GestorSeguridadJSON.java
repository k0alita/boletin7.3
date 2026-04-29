package Pruebas;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;

public class GestorSeguridadJSON {

    // 1. La clase debe usar @Expose solo en lo que queramos guardar
    public static class Usuario {
        @Expose // Este SÍ se guardará en el JSON
        private String username;

        // Al no poner @Expose, Gson lo ignorará completamente
        private String passwordSecreta;

        public Usuario(String username, String passwordSecreta) {
            this.username = username;
            this.passwordSecreta = passwordSecreta;
        }
    }

    // 2. El método para crear el Gson seguro
    public static Gson crearGsonSeguro() {
        return new GsonBuilder()
                .setPrettyPrinting()
                // ESTA ES LA LÍNEA MÁGICA: Le dice a Gson que solo mire los @Expose
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    // Ejemplo de uso:
    public static void probarSeguridad() {
        Usuario user = new Usuario("koalatech__", "12345_SuperSecreta");
        Gson gson = crearGsonSeguro();

        System.out.println(gson.toJson(user));
        // Resultado:
        // {
        //   "username": "koalatech__"
        // }
    }

    // 3. Borrar ficheros viejos (Más de X días)
    public static void limpiarLogsAntiguos(Path carpetaLogs, int diasAntiguedad) {
        // Calculamos la fecha límite (Hoy menos X días)
        Instant fechaLimite = Instant.now().minus(diasAntiguedad, ChronoUnit.DAYS);

        try (Stream<Path> archivos = Files.find(carpetaLogs, 1, (ruta, atributos) -> {
            boolean esLog = ruta.toString().endsWith(".log");
            boolean esViejo = atributos.lastModifiedTime().toInstant().isBefore(fechaLimite);
            return esLog && esViejo;
        })) {

            // Borramos cada archivo que haya superado el filtro
            archivos.forEach(ruta -> {
                try {
                    Files.delete(ruta);
                    System.out.println("Borrado por antigüedad: " + ruta.getFileName());
                } catch (IOException e) {
                    System.err.println("No se pudo borrar: " + ruta.getFileName());
                }
            });

        } catch (IOException e) {
            System.err.println("Error explorando carpeta: " + e.getMessage());
        }
    }
}