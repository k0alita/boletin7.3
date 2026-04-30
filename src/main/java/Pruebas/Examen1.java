import java.io.*;
import java.nio.file.*;
import java.util.regex.*;

public class Examen1 {
    public static void main(String[] args) throws IOException {
        String nombreFichero = "alumnos.txt";

        // Patrón regex: 3 palabras de mínimo 2 letras + edad entre 1 y 99
        // ^ y $ aseguran que la línea entera cumple el formato
        Pattern patron = Pattern.compile("^([A-Za-z]{2,})\\s([A-Za-z]{2,})\\s([A-Za-z]{2,})\\s([1-9][0-9]?)$");

        BufferedReader br = new BufferedReader(new FileReader(nombreFichero));
        String linea;
        int numLinea = 0;

        while ((linea = br.readLine()) != null) {
            numLinea++;

            // Intentamos hacer match con el patrón en cada línea
            Matcher m = patron.matcher(linea.trim());

            if (!m.matches()) {
                // Si no cumple el formato, avisamos y paramos el proceso
                System.out.println("Error en línea " + numLinea + ": " + linea);
                br.close();
                return;
            }

            // Extraemos los grupos capturados por el regex (grupo 1 = nombre, 2 = ap1, 3 = ap2)
            String nombre    = m.group(1);
            String apellido1 = m.group(2);
            String apellido2 = m.group(3);

            // Construimos el nombre de carpeta en formato Apellido1Apellido2Nombre
            String nombreCarpeta = apellido1 + apellido2 + nombre;
            Path carpeta = Paths.get(nombreCarpeta);

            // Solo creamos la carpeta si no existe ya
            if (!Files.exists(carpeta)) {
                Files.createDirectory(carpeta);
                System.out.println("Carpeta creada: " + nombreCarpeta);
            }

            // Creamos el fichero vacío dentro de la carpeta
            Path fichero = carpeta.resolve(nombreCarpeta + ".txt");
            if (!Files.exists(fichero)) {
                Files.createFile(fichero);
            }
        }

        br.close();
        System.out.println("Fichero válido. Líneas procesadas: " + numLinea);
    }
}