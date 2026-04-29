package RepasoExamen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Matricula {
    public static void main(String[] args) {
        Path ruta = Path.of("src\\main\\java\\RepasoExamen\\matriculas.txt");

        if (!Files.exists(ruta)) {
            System.out.println("El fichero no existe");
            return;
        }

        Pattern pattern = Pattern.compile("^(\\p{L}+)\\s+(?<numeros>\\d{4})-(?<letras>[B-Z&&[^EIOU]]{3})$");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (Stream<String> lineas = Files.lines(ruta)) {
            List<String> validas = lineas.filter(m -> pattern.matcher(m).matches())
                    .collect(Collectors.toList());

            try (FileWriter writer = new FileWriter("matriculas-correctas.json")){
                gson.toJson(validas, writer);
                System.out.println("Archivo matriculas-correctas.json creado correctamente");
            } catch (IOException e) {
                e.getMessage();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }




    }
}
