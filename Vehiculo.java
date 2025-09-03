// Importamos la librería Scanner para leer datos desde teclado 
import java.util.Scanner;

public class Vehiculo {
    public static void main(String[] args) {
        // Se crea un objeto Scanner para capturar la entrada del usuario
        Scanner sc = new Scanner(System.in);

        // Declaración de variables para el usuario
        String nombre;
        int edad;

        // Declaración de variables para el vehículo
        String marca;
        String modelo;
        String cilindrada;
        String combustible;
        int capacidad;

        // Captura de datos del usuario
        System.out.print("Ingrese su nombre: ");
        nombre = sc.nextLine();

        System.out.print("Ingrese su edad: ");
        edad = sc.nextInt();
        sc.nextLine(); // Limpiar buffer de entrada después de leer un int

        // Captura de datos del vehículo
        System.out.print("Ingrese la marca: ");
        marca = sc.nextLine();

        System.out.print("Ingrese el modelo: ");
        modelo = sc.nextLine();

        System.out.print("Ingrese la cilindrada: ");
        cilindrada = sc.nextLine();

        System.out.print("Ingrese el tipo de combustible: ");
        combustible = sc.nextLine();

        System.out.print("Ingrese la capacidad en pasajeros: ");
        capacidad = sc.nextInt();

        // Salida de datos del usuario
        System.out.println("\n--- Información del Usuario ---");
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad + " años");

        // Salida de datos del vehículo
        System.out.println("\n--- Información del Vehículo ---");
        System.out.println("La marca que ha ingresado es: " + marca);
        System.out.println("El modelo que ha ingresado es: " + modelo);
        System.out.println("La cilindrada que ha ingresado es: " + cilindrada);
        System.out.println("El tipo de combustible es: " + combustible);
        System.out.println("Tiene una capacidad de " + capacidad + " pasajeros.");
    }
}
