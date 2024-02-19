package br.unitins.topicos2.autopecas.resource;

import org.jboss.logging.Logger;

import br.unitins.topicos2.autopecas.dto.UsuarioDTO;
import br.unitins.topicos2.autopecas.dto.UsuarioResponseDTO;

import br.unitins.topicos2.autopecas.service.UsuarioService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {
    
    @Inject
    UsuarioService service;

    private static final Logger LOG = Logger.getLogger(UsuarioResource.class);

    @POST
    public Response create(UsuarioDTO dto) {
        UsuarioResponseDTO retorno = service.create(dto);
        //return Response.status(Status.CREATED).entity(retorno).build();
        return Response.status(201).entity(retorno).build();
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Response update(UsuarioDTO dto, @PathParam("id") Long id) {
        service.update(id, dto);
        return Response.status(Status.NO_CONTENT).build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    public Response findAll() {
        LOG.debug("Exemplo de debug.");
        return Response.ok(service.getAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(service.findById(id)).build();
    }
    
    @GET
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        return Response.ok(service.findByNome(nome)).build();
    }
}
