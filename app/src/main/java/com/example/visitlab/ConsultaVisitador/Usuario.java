package com.example.visitlab.ConsultaVisitador;

public class Usuario {

    private int id_codigo;
    private int dni;
    private String apellido;
    private String nombre;
    private String email;
    private String photo;
    private int citas_hechas;
    private int citas_totales;


    public int getId_codigo() {
        return id_codigo;
    }

    public void setId_codigo(int id_codigo) {
        this.id_codigo = id_codigo;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getCitas_hechas() {
        return citas_hechas;
    }

    public void setCitas_hechas(int citas_hechas) {
        this.citas_hechas = citas_hechas;
    }

    public int getCitas_totales() {
        return citas_totales;
    }

    public void setCitas_totales(int citas_totales) {
        this.citas_totales = citas_totales;
    }



    public Usuario(int id_codigo, int dni, String apellido, String nombre, String email, String photo, int citas_hechas, int citas_totales) {
        this.id_codigo = id_codigo;
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
        this.email = email;
        this.photo = photo;
        this.citas_hechas = citas_hechas;
        this.citas_totales = citas_totales;
    }


}
