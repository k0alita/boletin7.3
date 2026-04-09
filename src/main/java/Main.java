import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Persona> personas = new ArrayList<>();

        personas.add(new Persona("Alejandro Hernández", "12345678A", "600111222", LocalDate.of(2000,6,15)));
        personas.add(new Persona("María García", "87654321B", "600333444", LocalDate.of(2040,7,15)));
        personas.add(new Persona("Carlos López", "11223344C", "600555666", LocalDate.of(2020,5,23)));
        personas.add(new Persona("Laura Martínez", "44332211D", "600777888", LocalDate.of(1940,3,3)));
        personas.add(new Persona("Javier Sánchez", "55667788E", "600999000", LocalDate.of(2956,12,4)));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new TypeAdapter<LocalDate>() {
                    @Override
                    public void write(JsonWriter out, LocalDate value) throws IOException {
                        if (value == null) {
                            out.nullValue();
                        } else {
                            // Guarda la fecha como un String (ej. "2000-06-15")
                            out.value(value.format(formatter));
                        }
                    }

                    @Override
                    public LocalDate read(JsonReader in) throws IOException {
                        if (in.peek() == com.google.gson.stream.JsonToken.NULL) {
                            in.nextNull();
                            return null;
                        }
                        // Lee el String del JSON y lo convierte de nuevo a LocalDate
                        return LocalDate.parse(in.nextString(), formatter);
                    }
                })
                .setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter("personas.json")) {

            gson.toJson(personas, writer);
            System.out.println("Archivo generado");
        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }
    }
}
