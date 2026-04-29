package Pruebas;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class UtilidadesNIO {

    // 1. Copiar y Mover ficheros (¡Con sobrescritura!)
    public static void moverFichero(Path origen, Path destino) {
        try {
            // El REPLACE_EXISTING es clave para que no pete si el fichero ya existe en destino
            Files.move(origen, destino, StandardCopyOption.REPLACE_EXISTING);
            // Para copiar es igual: Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 2. Obtener información de un archivo rápidamente
    public static void infoFichero(Path ruta) {
        System.out.println("Nombre: " + ruta.getFileName()); // Solo el nombre.txt
        System.out.println("Ruta Padre: " + ruta.getParent()); // La carpeta que lo contiene
        System.out.println("Termina en .txt?: " + ruta.toString().endsWith(".txt"));

        try {
            System.out.println("Tamaño: " + Files.size(ruta) + " bytes");
            System.out.println("Está vacío?: " + (Files.size(ruta) == 0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 3. Buscar archivos que cumplan una condición (¡Mágico para exámenes!)
    // Este método busca en la carpeta indicada Y en todas sus subcarpetas.
    public static void buscarArchivos(Path carpetaRaiz) {
        try (Stream<Path> archivos = Files.find(
                carpetaRaiz,
                Integer.MAX_VALUE, // Profundidad máxima (infinito)
                (ruta, atributos) -> ruta.toString().endsWith(".json") && atributos.size() > 100)) {

            // Aquí procesas los que haya encontrado (ej: que terminen en .json y pesen más de 100 bytes)
            archivos.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 4. Recorrer todo el árbol de directorios
    public static void recorrerArbol(Path carpetaRaiz) {
        try (Stream<Path> rutas = Files.walk(carpetaRaiz)) { //Files.walk(carpetaRaiz, 4) para poner limite de arbol
            // Filtramos para que solo muestre archivos (no carpetas)
            rutas.filter(Files::isRegularFile)
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 5. Leer un fichero, quitar líneas en blanco y quitar espacios extra (trim)
    public static List<String> leerLimpiandoTexto(Path ruta) {
        try (Stream<String> lineas = Files.lines(ruta)) {
            return lineas
                    .map(String::trim)              // Quita espacios por los lados de cada línea
                    .filter(linea -> !linea.isEmpty()) // Elimina líneas que se han quedado vacías
                    .toList();
        } catch (IOException e) {
            return List.of();
        }
    }

    // 6. Contar cuántas palabras hay en TODO el fichero (Ejercicio 1 del Boletín 7.4)
    public static long contarPalabras(Path ruta) {
        try (Stream<String> lineas = Files.lines(ruta)) {
            return lineas
                    // flatMap separa las líneas en palabras usando Regex (uno o más espacios)
                    .flatMap(linea -> Arrays.stream(linea.split("\\s+")))
                    .filter(palabra -> !palabra.isEmpty())
                    .count(); // Cuenta el total del stream final
        } catch (IOException e) {
            return 0;
        }
    }

    // 7. Text Blocks para montar un JSON a mano
    public static String montarJsonAMano(String nombre, int edad) {
        // Las tres comillas (""") te permiten escribir texto en varias líneas
        // sin tener que poner \n o concatenar con +
        String json = """
            {
              "usuario": "%s",
              "edad": %d
            }
            """.formatted(nombre, edad); // .formatted sustituye %s por el nombre y %d por la edad

        return json;
    }

    // 1. Leer, ordenar alfabéticamente y guardar
    public static void ordenarFichero(Path rutaEntrada, Path rutaSalida) {
        try (Stream<String> lineas = Files.lines(rutaEntrada)) {

            List<String> lineasOrdenadas = lineas
                    // String.CASE_INSENSITIVE_ORDER ordena mezclando bien 'a' y 'A'
                    // Si no lo pones, pondría todas las Mayúsculas primero y luego minúsculas
                    .sorted(String.CASE_INSENSITIVE_ORDER)
                    .toList();

            // Escribimos el resultado
            Files.write(rutaSalida, lineasOrdenadas);
            System.out.println("Fichero ordenado correctamente.");

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // 2. Contar apariciones de una palabra ignorando comas, puntos y mayúsculas
    public static long contarPalabraEspecifica(Path ruta, String palabraBuscada) {
        try (Stream<String> lineas = Files.lines(ruta)) {
            return lineas
                    // El Regex [\\s.,;!?]+ divide por espacios O por cualquier signo de puntuación
                    .flatMap(linea -> Arrays.stream(linea.split("[\\s.,;!?]+")))
                    .filter(palabra -> palabra.equalsIgnoreCase(palabraBuscada))
                    .count();
        } catch (IOException e) {
            return 0;
        }
    }

    // 3. Crear un Gson capaz de entender y formatear LocalDate
    public static Gson crearGsonConFechas() {
        return new GsonBuilder()
                .setPrettyPrinting()
                // Registramos el traductor para LocalDate
                .registerTypeAdapter(LocalDate.class, new TypeAdapter<LocalDate>() {

                    @Override // Método para GUARDAR (Escribir en el JSON)
                    public void write(JsonWriter out, LocalDate value) throws IOException {
                        if (value == null) {
                            out.nullValue();
                        } else {
                            out.value(value.toString()); // Lo guarda limpio: "2026-04-30"
                        }
                    }

                    @Override // Método para LEER (Cargar desde el JSON)
                    public LocalDate read(JsonReader in) throws IOException {
                        if (in.peek() == JsonToken.NULL) {
                            in.nextNull();
                            return null;
                        }
                        return LocalDate.parse(in.nextString()); // Convierte el String de vuelta a LocalDate
                    }
                })
                .create(); // Crea el objeto Gson final
    }
}