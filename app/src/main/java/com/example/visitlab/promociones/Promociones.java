package com.example.visitlab.promociones;

public class Promociones {

    public Promociones(int id_codigo, String descripcion, String photo) {
        this.id_codigo = id_codigo;
        this.descripcion = descripcion;
        this.photo = photo;
    }


    public int getId_codigo() {
        return id_codigo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public String getphoto() {
        return photo;
    }

    private int id_codigo;
    private String descripcion;
    private String photo;

}
