package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    String nombre;
    String apellido;
    String apellido2;
    String fecha;
    String correo;
    String password;
    String idUsuario;

    private List<Objeto> listaObjetosComprados = null;

    public List<Objeto> getListaObjetosComprados() {
        return listaObjetosComprados;
    }

    public void setListaObjetosComprados(List<Objeto> listaObjetosComprados) {
        this.listaObjetosComprados = listaObjetosComprados;
    }

    double dsaCoins = 500;

    public Usuario(String nombre, String apellido, String apellido2, String fecha, String correo, String password) {
        setNombre(nombre);
        setApellido(apellido);
        setApellido2(apellido2);
        setFecha(fecha);
        setCorreo(correo);
        setPassword(password);
        this.listaObjetosComprados = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public double getDsaCoins() {
        return dsaCoins;
    }

    public void setDsaCoins(double dsaCoins) {
        this.dsaCoins = dsaCoins;
    }


}
