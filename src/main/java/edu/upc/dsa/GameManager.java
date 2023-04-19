package edu.upc.dsa;

import edu.upc.dsa.models.Objeto;
import edu.upc.dsa.models.Usuario;

import java.util.List;

public interface GameManager {

    //Lista de Usuarios ordenados alfabeticamente
    List <Usuario> listadeUsuariosOrdenadosAlfabeticamente();

    //Añadir Usuario
    public void addUsuario(String nombre, String apellido, String apellido2, String fecha, String correo, String password);

    //Login de usuario
    public void login(String correo, String password);

    //Añadir objeto
    public void addObjeto(String nombre, String descripcion, double precio);

    //Lista de objetos ordenados precio descendente

    List <Objeto> listadeObjetosOrdenadosPorPrecio();

    // Metodo hacer una compra
    public Objeto realizarCompra(String idUsuario, String nombreObjeto);

    //Lista de objetos que han sido comprados por un usuario
    List <Objeto> listaObjetosCompradosporUsuario(String idUsuario);

    public void clear();

    public Usuario getUsuarioPorNombre(String nombre);

    public Usuario getUsuarioPorCorreo(String correo);

    public Objeto getObjetoPorNombre(String nombre);

}

