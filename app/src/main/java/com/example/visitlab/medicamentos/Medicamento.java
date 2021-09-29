package com.example.visitlab.medicamentos;

public class Medicamento {

    public Medicamento(String nombre, String laboratorio, String especialidad, String photo) {
        this.nombre = nombre;
        this.laboratorio = laboratorio;
        this.especialidad = especialidad;
        this.photo = photo;
    }

    public String getNombre() {
        return nombre;
    }
    public String getLaboratorio() {
        return laboratorio;
    }
    public String getEspecialidad() {
        return especialidad;
    }
    public String getPhoto() {
        return photo;
    }

    private String nombre;
    private String laboratorio;
    private String especialidad;
    private String photo;
}