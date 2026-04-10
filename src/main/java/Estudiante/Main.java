package Estudiante;

import Videojuego.Videojuego;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArrayList<Estudiante> estudiantes = new ArrayList<>();

        Estudiante estudiante1 = new Estudiante("Alejandro", "DAM", 2.4);
        Estudiante estudiante2 = new Estudiante("Javier", "DAM", 5.3);
        Estudiante estudiante3 = new Estudiante("Moises", "SMR", 6.7);

        estudiantes.add(estudiante1);
        estudiantes.add(estudiante2);
        estudiantes.add(estudiante3);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {

            try (FileWriter writer = new FileWriter("estudiantes.json")) {
                gson.toJson(estudiantes, writer);
            }

            Type tipoLista = new TypeToken<List<Estudiante>>() {}.getType();

            try (FileReader reader = new FileReader("estudiantes.json")) {
                List<Estudiante> estudiantesLeidos = gson.fromJson(reader, tipoLista);

                estudiantesLeidos.forEach(System.out::println);
            }

        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
