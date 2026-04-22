package Registro_Usuarios;

import java.io.*;
import java.util.*;

public class usuarios {

    static final String ARCHIVO = "usuarios.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Usuarios registrados ===");
        mostrarUsuarios();

        System.out.println("\n=== Registrar nuevo usuario ===");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Edad: ");
        int edad = sc.nextInt();
        sc.nextLine();

        System.out.print("Correo electrónico: ");
        String correo = sc.nextLine();

        guardarUsuario(nombre, edad, correo);

        System.out.println("Usuario guardado correctamente.");
    }

    public static void mostrarUsuarios() {
        try {
            File archivo = new File(ARCHIVO);

            if (!archivo.exists()) {
                System.out.println("No hay usuarios registrados.");
                return;
            }

            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                System.out.println("Nombre: " + datos[0] +
                        " | Edad: " + datos[1] +
                        " | Email: " + datos[2]);
            }

            br.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo.");
        }
    }

    public static void guardarUsuario(String nombre, int edad, String correo) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO, true));
            bw.write(nombre + "," + edad + "," + correo);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            System.out.println("Error al guardar el usuario.");
        }
    }
}