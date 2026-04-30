package Pruebas;

import java.io.*;
import java.util.regex.*;

public class Examen2 {
    public static void main(String[] args) throws IOException {
        // Abrimos el fichero de entrada para lectura
        BufferedReader br = new BufferedReader(new FileReader("coches.txt"));

        // Abrimos (o creamos) el fichero de salida para escribir matrículas válidas
        BufferedWriter bw = new BufferedWriter(new FileWriter("MatriculasCorrectas.txt"));

        // Patrón de matrícula actual: exactamente 4 dígitos, guión, 3 letras mayúsculas
        Pattern patron = Pattern.compile("\\d{4}-[A-Z]{3}");

        String linea;
        while ((linea = br.readLine()) != null) {
            // Separamos la línea por espacios y cogemos la última parte (la matrícula)
            String[] partes = linea.trim().split(" ");
            String matricula = partes[partes.length - 1];

            Matcher m = patron.matcher(matricula);
            if (m.matches()) {
                // Si la matrícula es válida, la escribimos en el fichero de salida
                bw.write(matricula);
                bw.newLine(); // Salto de línea entre matrículas
                System.out.println("Matrícula válida: " + matricula);
            } else {
                System.out.println("Matrícula inválida: " + matricula);
            }
        }

        // Siempre cerrar los recursos al terminar
        br.close();
        bw.close();
        System.out.println("Fichero MatriculasCorrectas.txt generado.");
    }
}