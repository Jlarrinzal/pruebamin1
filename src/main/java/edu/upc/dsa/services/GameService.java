package edu.upc.dsa.services;


import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;
import edu.upc.dsa.models.Objeto;
import edu.upc.dsa.models.Usuario;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/Game", description = "Endpoint to Track Service")
@Path("/game")
public class GameService {

    private GameManager manager;

    public GameService() {
        this.manager = GameManagerImpl.getInstance();
        //if (manager.size()==0) {
          //  this.manager.addTrack("La Barbacoa", "Georgie Dann");
            //this.manager.addTrack("Despacito", "Luis Fonsi");
            //this.manager.addTrack("Enter Sandman", "Metallica");
        }



    //Añadimos usuario
    @POST
    @ApiOperation(value = "Añadir usuario", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= Usuario.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/AñadirUsuario")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUsuario(Usuario usuario) {

        if (usuario.getNombre()==null) return Response.status(500).entity(usuario).build();
        this.manager.addUsuario(usuario.getNombre(), usuario.getApellido(), usuario.getApellido2(), usuario.getFecha(), usuario.getCorreo(), usuario.getPassword());
        return Response.status(201).entity(usuario).build();
    }
    // get lista usuarios alfabeticamente
    @GET
    @ApiOperation(value = "lista usuarios", notes = "asdas" +
            "d")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class, responseContainer="List"),
    })
    @Path("/listaUsuarios")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getlistaUsuarios() {

        List<Usuario> usuario = this.manager.listadeUsuariosOrdenadosAlfabeticamente();

        GenericEntity<List<Usuario>> entity = new GenericEntity<List<Usuario>>(usuario) {};
        return Response.status(201).entity(entity).build();

    }
    //login
    @GET
    @ApiOperation(value = "get a login usuario", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class),
            @ApiResponse(code = 404, message = "No existe")
    })
    @Path("/login/{correo}{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@PathParam("correo") String correo, @PathParam("password") String password) {
        Usuario u = this.manager.getUsuarioPorCorreo(correo);
        if (u != null) {
            if (u.getPassword().equals(password)) {
                return Response.status(201).entity(u).build();
            }
        }
        return Response.status(404).build();
    }
    //Añadir objeto
    @POST
    @ApiOperation(value = "crear objeto nuevo", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= Objeto.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/addObjeto")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newObjeto(Objeto objeto) {

        if (objeto.getNombre()==null || objeto.getDescripcion()==null || objeto.getPrecio()==0.00)  return Response.status(500).entity(objeto).build();
        this.manager.addObjeto(objeto.getNombre(), objeto.getDescripcion(),objeto.getPrecio());
        return Response.status(201).entity(objeto).build();
    }
    //lista objetos
    @GET
    @ApiOperation(value = "lista objetos", notes = "asdas")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Objeto.class, responseContainer="List"),
    })
    @Path("/listaObjetos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getlistaObjetos() {

        List<Objeto> objeto = this.manager.listadeObjetosOrdenadosPorPrecio();

        GenericEntity<List<Objeto>> entity = new GenericEntity<List<Objeto>>(objeto) {};
        return Response.status(201).entity(entity).build();

    }
    // comprar objetos por parte de un usuario
    @POST
    @ApiOperation(value = "crear objeto nuevo", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/compraObjeto")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response compraObjeto(String idUsuario, String nombreObjeto) {
        Objeto objeto = this.manager.getObjetoPorNombre(nombreObjeto);
        Usuario usuario = this.manager.getUsuarioPorNombre(nombreObjeto);
        if (objeto.getNombre()==null || objeto.getDescripcion()==null)  return Response.status(500).build();
        this.manager.realizarCompra(usuario.getIdUsuario(), objeto.getNombre());
        return Response.status(201).entity(objeto).build();
    }
    //lista objetos comprados por usuario
    @GET
    @ApiOperation(value = "lista objetos comprados por usuario", notes = "asdas")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Objeto.class, responseContainer="List"),
    })
    @Path("/getlistaobjetoscompradosporusuario/{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getlistaObjetosCompradosporunUsuario(@PathParam("idUsuario") String idUsuario) {

        List<Objeto> objeto = this.manager.listaObjetosCompradosporUsuario(idUsuario);

        GenericEntity<List<Objeto>> entity = new GenericEntity<List<Objeto>>(objeto) {};
        return Response.status(201).entity(entity).build();

    }

    //*@DELETE
    //@ApiOperation(value = "delete a Track", notes = "asdasd")
    //@ApiResponses(value = {
        //    @ApiResponse(code = 201, message = "Successful"),
      //      @ApiResponse(code = 404, message = "Track not found")
    //})
    //@Path("/{id}")
    //public Response deleteTrack(@PathParam("id") String id) {
      //  Track t = this.manager.getTrack(id);
        //if (t == null) return Response.status(404).build();
       // else this.manager.deleteTrack(id);
     //   return Response.status(201).build();
    //}

    //@PUT
    //@ApiOperation(value = "update a Track", notes = "asdasd")
    //@ApiResponses(value = {
      //      @ApiResponse(code = 201, message = "Successful"),
        //    @ApiResponse(code = 404, message = "Track not found")
    //})
    //@Path("/")
    //public Response updateTrack(Track track) {

      //  Track t = this.manager.updateTrack(track);

        //if (t == null) return Response.status(404).build();

        //return Response.status(201).build();
    //}



    //@POST
    //@ApiOperation(value = "create a new Track", notes = "asdasd")
    //@ApiResponses(value = {
      //      @ApiResponse(code = 201, message = "Successful", response=Track.class),
        //    @ApiResponse(code = 500, message = "Validation Error")

    //})

    //@Path("/")
    //@Consumes(MediaType.APPLICATION_JSON)
    //public Response newTrack(Track track) {

      //  if (track.getSinger()==null || track.getTitle()==null)  return Response.status(500).entity(track).build();
        //this.manager.addTrack(track);
        //return Response.status(201).entity(track).build();
    //}

}