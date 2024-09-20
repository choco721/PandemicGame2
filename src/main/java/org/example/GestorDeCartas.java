package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GestorDeCartas {
    private List<Carta> mazoDeCartas;
    private List<Carta> cartasSacadas;

    public GestorDeCartas() {
        mazoDeCartas = new ArrayList<>();
        cartasSacadas = new ArrayList<>();
        inicializarMazo();
        barajarMazo();
    }

    private void inicializarMazo() {
        // Inicializa el mazo con cartas de enfermedades
        for (int i = 0; i < 10; i++) { // Suponiendo 10 cartas por enfermedad
            mazoDeCartas.add(new Carta("Enfermedad", "Enfermedad1"));
            mazoDeCartas.add(new Carta("Enfermedad", "Enfermedad2"));
            mazoDeCartas.add(new Carta("Enfermedad", "Enfermedad3"));
            mazoDeCartas.add(new Carta("Enfermedad", "Enfermedad4"));
        }
        // Añadir cartas de eventos, si es necesario
        mazoDeCartas.add(new Carta("Evento", "Evento1"));
        mazoDeCartas.add(new Carta("Evento", "Evento2"));
    }

    private void barajarMazo() {
        Collections.shuffle(mazoDeCartas);
    }

    public Carta sacarCarta() {
        if (!mazoDeCartas.isEmpty()) {
            Carta carta = mazoDeCartas.remove(0);
            cartasSacadas.add(carta);
            return carta;
        }
        return null;
    }

    public void mostrarCartasSacadas() {
        System.out.println("Cartas sacadas:");
        for (Carta carta : cartasSacadas) {
            System.out.println(carta);
        }
    }

    public boolean estaVacio() {
        return mazoDeCartas.isEmpty();
    }
}
