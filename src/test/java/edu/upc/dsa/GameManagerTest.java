package edu.upc.dsa;

import edu.upc.dsa.models.Objeto;
import edu.upc.dsa.models.Usuario;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class GameManagerTest {

    GameManager manager = GameManagerImpl.getInstance();

    Logger logger = Logger.getLogger(GameManagerTest.class);
    @Before
    public void Inicializar() {
        manager = new GameManagerImpl();
        manager.addUsuario("Jose", "Larrinzal", "Jimenez", "090700", "jose.maria.larrinzal@estudiantat.upc.edu", "supersegura1");
        manager.addUsuario("A", "A", "Ji", "090700", "jose.larrinzal@estudiantat.upc.edu", "supersegura2");

        manager.addObjeto("pikachu","pokemon", 59.99);
        manager.addObjeto("charmander","pokemon", 45.99);

    }

    @Test
    public void listadeUsuariosOrdenadosAlfabeticamenteTest() {
        List<Usuario> resultado = manager.listadeUsuariosOrdenadosAlfabeticamente();
        Assert.assertEquals("Larrinzal",resultado.get(0).getApellido());
        Assert.assertEquals("A",resultado.get(1).getApellido());
    }

    @Test
    public void addUsuario() {
        manager.addUsuario("Hola","Prueba","xd", "090909","asdsd","asd");
    }

    @Test
    public void Compra(){
        manager.realizarCompra("Jose", "charmander");
        manager.realizarCompra("Jose", "pikachu");
        manager.realizarCompra("A", "pikachu");
    }

    @Test
    public void listadeObjetosOrdenadosPorPrecio() {

        List<Objeto> resultado = manager.listadeObjetosOrdenadosPorPrecio();

        Assert.assertEquals(55.99,resultado.get(0).getPrecio(),0.25);
        Assert.assertEquals(59.99,resultado.get(1).getPrecio(),0.25);
    }

    @Test
    public void listaObjetosCompradosporUsuario(){

        logger.info(manager.realizarCompra("Jose", "pikachu"));
    }

    @After
    public void tearDown(){
        manager.clear();
    }

}
