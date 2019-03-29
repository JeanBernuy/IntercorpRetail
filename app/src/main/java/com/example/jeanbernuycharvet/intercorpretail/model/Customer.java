package com.example.jeanbernuycharvet.intercorpretail.model;

public class Customer {

    private String nombre;
    private String apellidos;
    private String edad;
    private String fecha;

    public Customer() {
    }

    public Customer(String nombre, String apellidos, String edad, String fecha) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
