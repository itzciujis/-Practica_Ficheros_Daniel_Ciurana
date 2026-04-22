package Sistema_CRUD;

import java.io.*;
import java.util.*;

public class CRUD {

    static String archivo = "libros.txt";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== SISTEMA CRUD BIBLIOTECA ===");
            System.out.println("1. Crear libro");
            System.out.println("2. Leer libros");
            System.out.println("3. Actualizar libro");
            System.out.println("4. Eliminar libro");
            System.out.println("0. Salir");
            System.out.print("Opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> crearLibro(sc);
                case 2 -> leerLibros();
                case 3 -> actualizarLibro(sc);
                case 4 -> eliminarLibro(sc);
            }

        } while (opcion != 0);
    }

    public static void crearLibro(Scanner sc) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {

            System.out.print("Título: ");
            String titulo = sc.nextLine();

            System.out.print("Autor: ");
            String autor = sc.nextLine();

            System.out.print("Año: ");
            String año = sc.nextLine();

            bw.write(titulo + ";" + autor + ";" + año);
            bw.newLine();

        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public static void leerLibros() {

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(";");

                System.out.println(datos[0] + " | " + datos[1] + " | " + datos[2]);
            }

        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public static void actualizarLibro(Scanner sc) {

        System.out.print("Título a actualizar: ");
        String buscar = sc.nextLine();

        List<String> libros = new ArrayList<>();
        boolean encontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(";");

                if (datos[0].equalsIgnoreCase(buscar)) {

                    System.out.print("Nuevo título: ");
                    String titulo = sc.nextLine();

                    System.out.print("Nuevo autor: ");
                    String autor = sc.nextLine();

                    System.out.print("Nuevo año: ");
                    String año = sc.nextLine();

                    libros.add(titulo + ";" + autor + ";" + año);
                    encontrado = true;

                } else {
                    libros.add(linea);
                }
            }

        } catch (IOException e) {
            System.out.println("Error");
        }

        guardarArchivo(libros);
    }

    public static void eliminarLibro(Scanner sc) {

        System.out.print("Título a eliminar: ");
        String buscar = sc.nextLine();

        List<String> libros = new ArrayList<>();
        boolean eliminado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(";");

                if (!datos[0].equalsIgnoreCase(buscar)) {
                    libros.add(linea);
                } else {
                    eliminado = true;
                }
            }

        } catch (IOException e) {
            System.out.println("Error");
        }

        guardarArchivo(libros);
    }

    public static void guardarArchivo(List<String> libros) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {

            for (String libro : libros) {
                bw.write(libro);
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}