package Pruebas;

import java.io.*;
import java.util.*;

public class Examen3 {
    public static void main(String[] args) throws IOException {
        String nombreFichero = "texto.txt";

        // Lista para guardar todas las líneas del fichero
        List<String> lineas = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(nombreFichero));
        String linea;
        int totalPalabras = 0;

        while ((linea = br.readLine()) != null) {
            lineas.add(linea); // Guardamos la línea para usarla después en la búsqueda

            // Separamos por espacios o saltos para contar palabras
            String[] palabras = linea.trim().split("[\\s]+");
            if (!linea.trim().isEmpty()) totalPalabras += palabras.length;
        }
        br.close();

        System.out.println("Total de palabras: " + totalPalabras);

        // Pedimos la palabra a buscar por teclado
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce palabra a buscar: ");
        String palabraBuscar = sc.nextLine().trim();

        boolean encontrada = false;

        // Creamos el fichero resultado con el nombre de la palabra buscada
        BufferedWriter bw = new BufferedWriter(new FileWriter("BuscandoPalabra" + palabraBuscar + ".txt"));

        // Recorremos línea por línea y palabra por palabra
        for (int i = 0; i < lineas.size(); i++) {
            String[] palabrasLinea = lineas.get(i).split("[\\s]+");
            for (int j = 0; j < palabrasLinea.length; j++) {
                // Comparamos ignorando mayúsculas/minúsculas
                if (palabrasLinea[j].equalsIgnoreCase(palabraBuscar)) {
                    // indexOf nos da la posición del carácter en la línea (+1 para que empiece en 1)
                    int columna = lineas.get(i).indexOf(palabrasLinea[j]) + 1;
                    String resultado = "Encontrada en línea " + (i + 1) + " columna " + columna;
                    System.out.println(resultado);
                    bw.write(resultado);
                    bw.newLine();
                    encontrada = true;
                }
            }
        }

        bw.close();
        if (!encontrada) System.out.println("Palabra no encontrada.");
        sc.close();
    }
}