package com.example.visitlab.ubicaciones;

public class Ubicaciones {

    public Ubicaciones(int id_codigo, int dni, String ubi_X, String ubi_Y, String fecha, String hora) {
        this.id_codigo = id_codigo;
        this.dni = dni;
        this.ubi_X = ubi_X;
        this.ubi_Y = ubi_Y;
        this.fecha = fecha;
        this.hora = hora;
    }

    public int getId_codigo() {return id_codigo;}
    public int getDni() {return dni;}

    public String getUbi_X() {
        return ubi_X;
    }

    public String getUbi_Y() {
        return ubi_Y;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }


    private int id_codigo;
    private int dni;
    private String ubi_X;
    private String ubi_Y;
    private String fecha;
    private String hora;
}
