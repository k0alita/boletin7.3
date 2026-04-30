package Pruebas;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

// Clase modelo que representa un videojuego
class Videojuego {
    String titulo;
    String desarrolladora;
    int anioLanzamiento;

    public Videojuego(String titulo, String desarrolladora, int anioLanzamiento) {
        this.titulo = titulo;
        this.desarrolladora = desarrolladora;
        this.anioLanzamiento = anioLanzamiento;
    }

    @Override
    public String toString() {
        return titulo + " (" + anioLanzamiento + ") - " + desarrolladora;
    }
}

public class Examen4 {
    public static void main(String[] args) throws IOException {
        // GsonBuilder con pretty printing para JSON más legible
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // FileReader abre el fichero JSON para leerlo
        FileReader fr = new FileReader("videojuegos.json");

        // TypeToken le dice a Gson que el JSON es una List<Videojuego> (no solo un objeto)
        Type listType = new TypeToken<List<Videojuego>>() {}.getType();

        // fromJson convierte el JSON a una lista de objetos Java
        List<Videojuego> juegos = gson.fromJson(fr, listType);
        fr.close();

        // Usamos Stream para filtrar, ordenar y mostrar
        juegos.stream()
                .filter(j -> j.anioLanzamiento > 2010)          // Solo los de después del 2010
                .sorted((a, b) -> b.anioLanzamiento - a.anioLanzamiento) // Orden descendente
                .forEach(System.out::println);                   // Imprime con toString()
    }
}