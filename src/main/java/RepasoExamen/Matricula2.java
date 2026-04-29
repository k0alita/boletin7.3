package RepasoExamen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Matricula2 {
    public static void main(String[] args) {
        Path ruta = Path.of("src\\main\\java\\RepasoExamen\\matriculas.txt");

        if (!Files.exists(ruta)) {
            System.out.println("El fichero no existe");
            return;
        }

        Pattern pattern = Pattern.compile("^(\\p{L}+)\\s+(?<numeros>\\d{4})-(?<letras>[B-Z&&[^EIOU]]{3})$");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (Stream<String> lineas = Files.lines(ruta)) {
            List<Object> resultado = lineas
                    .map(linea -> pattern.matcher(linea))
                    .filter(m -> m.matches())
                    .map(m -> new MatriculaR (
                            m.group("numeros"),
                            m.group("letras")
                    ))
                    .collect(Collectors.toList());

            try (FileWriter writer = new FileWriter("matriculas-detalle.json")) {
                gson.toJson(resultado, writer);
                System.out.println("Se han guardado las matriculas " + resultado.size());
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    record MatriculaR(String numeros, String letras) {

    }
}
