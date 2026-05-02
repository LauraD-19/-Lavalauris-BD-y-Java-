package org.example.Model;

public class Servicios {

    private int servicio_id;
    private String nombre;
    private double precio;

    public Servicios() {
    }

    public Servicios(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getServicio_id() {
        return servicio_id;
    }

    public void setServicio_id(int servicio_id) {
        this.servicio_id = servicio_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "--->\n" +
                "ID servicio= "+servicio_id+'\'' +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio;
    }
}
