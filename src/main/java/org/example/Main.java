package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Tablero tablero = new Tablero();
        GestorDeCartas gestorDeCartas = new GestorDeCartas();
        List<Jugador> jugadores = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // Crear ciudades
        Ciudad newYork = new Ciudad("New York");
        Ciudad losAngeles = new Ciudad("Los Angeles");
        Ciudad chicago = new Ciudad("Chicago");
        Ciudad miami = new Ciudad("Miami");

        // Añadir ciudades al tablero
        tablero.addCiudad(newYork);
        tablero.addCiudad(losAngeles);
        tablero.addCiudad(chicago);
        tablero.addCiudad(miami);

        // Conectar ciudades
        tablero.conectarCiudades("New York", "Los Angeles");
        tablero.conectarCiudades("Chicago", "Miami");

        // Añadir jugadores
        System.out.println("¡Bienvenidos al juego Pandemic Game!");
        for (int i = 0; i < 4; i++) {
            System.out.print("Ingrese el nombre del jugador " + (i + 1) + ": ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese el rol del jugador " + (i + 1) + " (Médico, Científico, Maestro, Comandante): ");
            String rol = scanner.nextLine();
            jugadores.add(new Jugador(nombre, rol));
        }

        boolean juegoEnCurso = true;
        int turnoActual = 0;

        while (juegoEnCurso) {
            Jugador jugadorActual = jugadores.get(turnoActual);
            System.out.println("\n--- Turno de " + jugadorActual.getNombre() + " (" + jugadorActual.getRol() + ") ---");
            System.out.println("1. Mostrar estado de las ciudades");
            System.out.println("2. Infectar ciudad");
            System.out.println("3. Curar ciudad");
            System.out.println("4. Mostrar jugadores");
            System.out.println("5. Mostrar conexiones del tablero");
            System.out.println("6. Sacar carta de infección");
            System.out.println("7. Salir");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.println("Estado de las ciudades:");
                    for (Ciudad ciudad : tablero.getCiudades().values()) {
                        System.out.println(ciudad);
                    }
                    break;
                case 2:
                    System.out.print("Ingrese el nombre de la ciudad para infectar: ");
                    String ciudadInfectar = scanner.nextLine();
                    Ciudad ciudadInfectada = tablero.getCiudad(ciudadInfectar);
                    if (ciudadInfectada != null) {
                        ciudadInfectada.infectar();
                        System.out.println("La ciudad " + ciudadInfectada.getNombre() + " ha sido infectada.");
                    } else {
                        System.out.println("Ciudad no encontrada.");
                    }
                    break;
                case 3:
                    System.out.print("Ingrese el nombre de la ciudad para curar: ");
                    String ciudadCurar = scanner.nextLine();
                    Ciudad ciudadCurada = tablero.getCiudad(ciudadCurar);
                    if (ciudadCurada != null) {
                        ciudadCurada.curar();
                        System.out.println("La ciudad " + ciudadCurada.getNombre() + " ha sido curada.");
                    } else {
                        System.out.println("Ciudad no encontrada.");
                    }
                    break;
                case 4:
                    for (Jugador jugador : jugadores) {
                        System.out.println(jugador);
                    }
                    break;
                case 5:
                    tablero.mostrarConexiones();
                    break;
                case 6:
                    Carta cartaSacada = gestorDeCartas.sacarCarta();
                    if (cartaSacada != null) {
                        System.out.println("Carta sacada: " + cartaSacada);
                    } else {
                        System.out.println("No hay más cartas en el mazo.");
                    }
                    break;
                case 7:
                    System.out.println("¡Gracias por jugar!");
                    juegoEnCurso = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

            // Chequear condiciones de victoria y derrota
            if (tablero.hayBrotes()) {
                System.out.println("¡El juego ha terminado! Los brotes han alcanzado el límite.");
                juegoEnCurso = false;
            }

            if (tablero.todasLasCurasEncontradas()) {
                System.out.println("¡Victoria! Se han encontrado todas las curas.");
                juegoEnCurso = false;
            }

            if (gestorDeCartas.estaVacio()) {
                System.out.println("¡El juego ha terminado! No quedan cartas en el mazo.");
                juegoEnCurso = false;
            }

            // Pasar al siguiente turno
            turnoActual = (turnoActual + 1) % jugadores.size();
        }

        scanner.close();
    }
}
