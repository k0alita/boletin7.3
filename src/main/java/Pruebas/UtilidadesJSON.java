package Pruebas;

import Estudiante.Estudiante;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UtilidadesJSON {

    // Instancia de Gson configurada para que quede bonito (PrettyPrinting)
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    // 6. Guardar una Lista de Objetos a JSON
    public static void guardarListaJSON(String ruta, List<Estudiante> lista) {
        try {
            String json = GSON.toJson(lista);
            Files.writeString(Paths.get(ruta), json);
        } catch (IOException e) {
            System.out.println("Error guardando JSON: " + e.getMessage());
        }
    }

    // 7. Cargar una Lista de Objetos desde JSON (Ojo al TypeToken)
    public static List<Estudiante> cargarListaJSON(String ruta) {
        try {
            String jsonLeido = Files.readString(Paths.get(ruta));

            // Magia para que Java recuerde que es una Lista de Estudiantes
            Type tipoLista = new TypeToken<ArrayList<Estudiante>>(){}.getType();

            return GSON.fromJson(jsonLeido, tipoLista);

        } catch (IOException e) {
            // Si el archivo no existe, devolvemos una lista vacía para no romper el programa
            return new ArrayList<>();
        }
    }


}