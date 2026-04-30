package Pruebas;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

// Clase para el objeto anidado dentro de Usuario
class Direccion {
    String calle, ciudad, codigoPostal;
}

class Usuario {
    String username;

    // "transient" le indica a Java (y a Gson) que este campo NO debe serializarse
    // Es decir, no aparecerá en ningún JSON de salida
    transient String password;

    String email;
    Direccion direccion; // Objeto anidado — Gson lo maneja automáticamente
}

public class Examen5 {
    public static void main(String[] args) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Leemos y deserializamos la lista de usuarios desde el JSON
        FileReader fr = new FileReader("usuarios.json");
        Type listType = new TypeToken<List<Usuario>>() {}.getType();
        List<Usuario> usuarios = gson.fromJson(fr, listType);
        fr.close();

        // Mostramos username y ciudad accediendo al objeto anidado direccion
        for (Usuario u : usuarios) {
            System.out.println(u.username + " vive en " + u.direccion.ciudad);
        }

        // Re-serializamos la lista — como password es transient, no aparecerá en el JSON
        FileWriter fw = new FileWriter("usuariosSinPassword.json");
        gson.toJson(usuarios, fw);
        fw.close();

        System.out.println("\nGuardado en usuariosSinPassword.json (sin passwords).");
    }
}