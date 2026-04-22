package Lista_Epstein;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Epstein {

    public static void main(String[] args) {

        String archivo = "lista.txt";
        String palabraBuscar = "Trump";

        int contador = contarPalabra(archivo, palabraBuscar);

        System.out.println(palabraBuscar + ": " + contador);
    }

    public static int contarPalabra(String ruta, String palabra) {

        int contador = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {

            String linea;
            palabra = palabra.toLowerCase();

            while ((linea = br.readLine()) != null) {

                linea = linea.toLowerCase();

                String[] palabras = linea.split("[\\s,.;:!?()\\[\\]\"]+");

                for (String p : palabras) {
                    if (p.equals(palabra)) {
                        contador++;
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error");
        }

        return contador;
    }
}