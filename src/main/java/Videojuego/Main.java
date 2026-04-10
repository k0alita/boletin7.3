package Videojuego;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Videojuego videojuego = new Videojuego("pepe el mono", "koala studios", 2025);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {

            try (FileWriter writer = new FileWriter("videojuego.json");
                 FileReader reader = new FileReader("videojuego.json")) {

                gson.toJson(videojuego, writer);
                writer.flush();
                Videojuego videojuegoLeido = gson.fromJson(reader, Videojuego.class);
                System.out.println(videojuegoLeido.toString());

            }
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
