package Direccion;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Direccion direccion = new Direccion("pepino", "Pepo", 12401);
        Usuario usuario = new Usuario("koalitaa",  "1234567A", "koalita@gmail.com", direccion);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter("usuario.json")) {
            gson.toJson(usuario, writer);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
