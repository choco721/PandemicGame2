package org.example;

public class Carta {
    private String tipo;
    private String contenido;

    public Carta(String tipo, String contenido) {
        this.tipo = tipo;
        this.contenido = contenido;
    }


    public String toString() {
        return tipo + ": " + contenido;
    }
}
