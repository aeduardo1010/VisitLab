package com.example.visitlab.ConsultaVisitador;

public class Visitadores {


    public Visitadores(int id_codigo, int dni, String apellido, String nombre, String email, String photo, int citas_hechas, int citas_totales) {
        this.id_codigo = id_codigo;
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
        this.email = email;
        this.photo = photo;
        this.citas_hechas = citas_hechas;
        this.citas_totales = citas_totales;
    }


    public int getId_codigo() {
        return id_codigo;
    }

    public int getDni() {
        return dni;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getphoto() {
        return photo;
    }

    public int getCitas_hechas() {
        return citas_hechas;
    }

    public int getCitas_totales() {
        return citas_totales;
    }

    private int id_codigo;
    private int dni;
    private String apellido;
    private String nombre;
    private String email;
    private String photo;
    private int citas_hechas;
    private int citas_totales;
}
