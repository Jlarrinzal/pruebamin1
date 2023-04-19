package edu.upc.dsa;

import edu.upc.dsa.models.Objeto;
import edu.upc.dsa.models.Usuario;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;


public class GameManagerImpl implements GameManager{

    HashMap<String, Usuario> Usuarios;
    List<Usuario> listaUsuarios;
    HashMap<String, Objeto> Objetos;
    List<Objeto> listaObjetos;
    private static GameManagerImpl manager;

    public GameManagerImpl(){
        this.listaUsuarios = new ArrayList<>();
        this.Usuarios = new HashMap<>();
        this.listaObjetos = new ArrayList<>();
        this.Objetos = new HashMap<>();
    }

    public static GameManagerImpl getInstance(){
        if (manager == null){
            manager = new GameManagerImpl();
        }
        return manager;
    }

    static final Logger logger = Logger.getLogger(GameManagerImpl.class.getName());
    @Override
    public List<Usuario> listadeUsuariosOrdenadosAlfabeticamente() {
        this.listaUsuarios.sort(new Comparator<Usuario>() {
            @Override
            public int compare(Usuario o1, Usuario o2) {
                if (o1.getNombre().compareTo(o2.getNombre()) == 0) {
                    return o2.getApellido().compareTo(o1.getApellido());
                } else {
                    return o2.getNombre().compareTo(o1.getNombre());
                }
            }
        });
        return this.listaUsuarios;

    }

    @Override
    public void addUsuario(String nombre, String apellido, String apellido2, String fecha, String correo, String password) {

        if (Usuarios.get(correo) == null){

            this.listaUsuarios.add(new Usuario(nombre, apellido, apellido2, fecha, correo, password));

            logger.info("Se ha realizado correctamente");
        }
        else
            logger.info("El correo ya existe con un usuario");
    }

    @Override
    public void login(String correo, String password) {

        Usuario usuario = this.Usuarios.get(correo);
        if(usuario.getPassword().equals(password)){
            logger.info("Login con exito");
        }
        logger.info("Contraseña incorrecta");

    }

    @Override
    public void addObjeto(String nombre, String descripcion, double precio) {

        this.listaObjetos.add(new Objeto(nombre, descripcion, precio));

    }

    @Override
    public List<Objeto> listadeObjetosOrdenadosPorPrecio() {
        this.listaObjetos.sort(new Comparator<Objeto>() {
            @Override
            public int compare(Objeto o1, Objeto o2) {
                return Double.compare(o1.getPrecio(), o2.getPrecio());
            }
        });
        logger.info("Lista ordenada alfabeticamente: " + listaObjetos.toString());
        return listaObjetos;
    }

    @Override
    public Objeto realizarCompra(String idUsuario, String nombreObjeto) {
        Usuario usuario = getUsuarioPorNombre(idUsuario);
        if (usuario == null) {
            logger.info("Usuario " + idUsuario + "no existe");
        }
        else {
            Objeto objeto = getObjetoPorNombre(nombreObjeto);
            if (usuario.getDsaCoins() < objeto.getPrecio()) {
                logger.info("No tienes money");
            }
            else{
                usuario.getListaObjetosComprados().add(objeto);
                double saldo = usuario.getDsaCoins() - objeto.getPrecio();
                usuario.setDsaCoins(saldo);
                logger.info("Objeto" + nombreObjeto + "comprado");
                logger.info(idUsuario + "ahora tienes:" + saldo + "dsaCoins");
                return objeto;
            }
        }
        return null;
    }

    @Override
    public List<Objeto> listaObjetosCompradosporUsuario(String idUsuario) {
        Usuario usuario = getUsuarioPorNombre(idUsuario);
        return usuario.getListaObjetosComprados();
    }

    // metodos extra
    public Usuario getUsuarioPorNombre(String nombre){
        for (Usuario u: this.listaUsuarios) {
            if(u.getNombre().equals(nombre)){
                return u;
            }
        }
        return null;
    }

    public Usuario getUsuarioPorCorreo(String correo){
        for (Usuario u: this.listaUsuarios) {
            if(u.getCorreo().equals(correo)){
                return u;
            }
        }
        return null;
    }

    public Objeto getObjetoPorNombre(String nombre){
        for (Objeto o: this.listaObjetos) {
            if(o.getNombre().equals(nombre)){
                return o;
            }
        }
        return null;
    }

    public void clear() {
        this.listaUsuarios.clear();
        this.listaObjetos.clear();
    }
}
