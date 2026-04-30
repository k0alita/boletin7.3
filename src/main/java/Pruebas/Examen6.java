package Pruebas;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.*;
import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.*;

class Evento {
    String nombre;
    LocalDate fecha;
}

public class Examen6 {

    // TypeAdapter le enseña a Gson cómo leer y escribir un tipo que no soporta nativamente
    // En este caso, LocalDate lo convertimos a/desde String "YYYY-MM-DD"
    static TypeAdapter<LocalDate> localDateAdapter = new TypeAdapter<LocalDate>() {

        @Override
        public void write(JsonWriter out, LocalDate value) throws IOException {
            // Serialización: LocalDate -> String (ej: "2024-03-15")
            out.value(value.toString());
        }

        @Override
        public LocalDate read(JsonReader in) throws IOException {
            // Deserialización: String -> LocalDate usando parse()
            return LocalDate.parse(in.nextString());
        }
    };

    public static void main(String[] args) throws IOException {
        // Registramos el TypeAdapter para que Gson lo use cuando encuentre un LocalDate
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, localDateAdapter)
                .create();

        // Leemos el fichero JSON y deserializamos con el adaptador configurado
        FileReader fr = new FileReader("eventos.json");
        Type listType = new TypeToken<List<Evento>>() {}.getType();
        List<Evento> eventos = gson.fromJson(fr, listType);
        fr.close();

        // Ordenamos usando compareTo de LocalDate (de más reciente a más antiguo)
        eventos.sort((a, b) -> b.fecha.compareTo(a.fecha));

        // Mostramos cada evento con su fecha ya parseada correctamente
        for (Evento e : eventos) {
            System.out.println(e.nombre + " — " + e.fecha);
        }
    }
}