package edu.upc.dsa.models;

public class Objeto {


    String nombre;

    String descripcion;

    double precio;


    public Objeto(String nombre, String descripcion, double precio){

        setNombre(nombre);
        setDescripcion(descripcion);
        setPrecio(precio);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }


}
