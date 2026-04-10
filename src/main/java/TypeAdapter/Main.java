package TypeAdapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {

        Evento evento = new Evento("Fin de Temporada C4S2", LocalDate.of(2022, 7, 16));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new TypeAdapter<LocalDate>() {
                    @Override
                    public void write(JsonWriter jsonWriter, LocalDate localDate) throws IOException {
                        if (localDate == null) {
                            jsonWriter.nullValue();
                        } else {
                            jsonWriter.value(localDate.format(formatter));
                        }
                    }

                    @Override
                    public LocalDate read(JsonReader jsonReader) throws IOException {
                        return LocalDate.parse(jsonReader.nextString(), formatter);
                    }
                })
                .setPrettyPrinting().create();

        try {

        try(FileWriter writer = new FileWriter("evento.json")) {
            gson.toJson(evento, writer);
        }

        try(FileReader reader = new FileReader("evento.json")) {
            Evento eventoLeido = gson.fromJson(reader, Evento.class);

            System.out.println(eventoLeido);
        }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
