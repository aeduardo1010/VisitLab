package com.example.visitlab.perfil;

public class Visitador {

    private String nombre;
    private String genero;
    private String año;

    public Visitador(String nombre, String genero, String año) {
        this.nombre = nombre;
        this.genero = genero;
        this.año = año;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

}
