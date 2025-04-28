package com.br.neostore.adapters.in.controller;

import com.br.neostore.adapters.in.controller.mapper.FornecedorMapper;
import com.br.neostore.adapters.in.controller.request.FornecedorRequest;
import com.br.neostore.adapters.in.controller.response.PaginacaoFornecedorResponse;
import com.br.neostore.application.ports.in.*;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/fornecedores")
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class FornecedorController {

    private final InsertFornecedorInputPort insertFornecedorInputPort;
    private ListarFornecedorInputPort listarFornecedorInputPort;
    private final FindFornecedorByIdInputPort findFornecedorByIdInputPort;
    private final DeleteFornecedorByIdInputPort  deleteFornecedorByIdInputPort;
    private final UpdateFornecedorByIdInputPort updateFornecedorByIdInputPort;
    private final ImportarFornecedoresInputPort importarFornecedoresInputPort;
    private final ListarFornecedorPaginadoInputPort listarFornecedorPaginadoInputPort;
    private FornecedorMapper fornecedorMapper;

    @Inject
    public FornecedorController(InsertFornecedorInputPort insertFornecedorInputPort,
        ListarFornecedorInputPort listarFornecedorInputPort,
        FindFornecedorByIdInputPort findFornecedorByIdInputPort,
        DeleteFornecedorByIdInputPort deleteFornecedorByIdInputPort,
        UpdateFornecedorByIdInputPort updateFornecedorByIdInputPort,
        ImportarFornecedoresInputPort importarFornecedoresInputPort,
        ListarFornecedorPaginadoInputPort listarFornecedorPaginadoInputPort,
        FornecedorMapper fornecedorMapper)
    {
        this.insertFornecedorInputPort = insertFornecedorInputPort;
        this.listarFornecedorInputPort = listarFornecedorInputPort;
        this.findFornecedorByIdInputPort = findFornecedorByIdInputPort;
        this.deleteFornecedorByIdInputPort = deleteFornecedorByIdInputPort;
        this.updateFornecedorByIdInputPort = updateFornecedorByIdInputPort;
        this.importarFornecedoresInputPort = importarFornecedoresInputPort;
        this.listarFornecedorPaginadoInputPort = listarFornecedorPaginadoInputPort;
        this.fornecedorMapper = fornecedorMapper;
    }

    @GET
    public Response listarTodos() {
        var fornecedores = listarFornecedorInputPort.execute();
        return Response.ok(fornecedores.stream()
                        .map(fornecedorMapper::toResponse)
                        .collect(Collectors.toList()))
                .build();
    }

    @GET
    @Path("/paginado")
    public Response listarPaginado(@QueryParam("page") @DefaultValue("1") int page,
        @QueryParam("tamanho") @DefaultValue("5") int size)
    {
        var fornecedores = listarFornecedorPaginadoInputPort.listarPaginado(page, size);
        long totalRegistros = listarFornecedorPaginadoInputPort.contar();
        long totalPaginas = (long) Math.ceil((double)totalRegistros/size);
        var response = new PaginacaoFornecedorResponse<>(
            fornecedorMapper.toResponseList(fornecedores),
            page,
            size,
            totalRegistros,
            totalPaginas
        );

        return Response.ok(response).build();
    }

   @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        var fornecedor = findFornecedorByIdInputPort.execute(id);
        if (fornecedor == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(fornecedorMapper.toResponse(fornecedor)).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, @Valid FornecedorRequest fornecedorRequest)
    {
        try
        {
            return Response.ok(updateFornecedorByIdInputPort.execute(id, fornecedorMapper.toDomain(fornecedorRequest)))
                .build();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @POST
    public Response criar(@Valid FornecedorRequest fornecedorRequest)
    {
        try
        {
            return Response.status(Response.Status.CREATED)
                .entity(insertFornecedorInputPort.execute(fornecedorMapper.toDomain(fornecedorRequest)))
                .build();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/importar")
    public Response importar(@Valid List<FornecedorRequest> fornecedorRequests)
    {
        try
        {
            var fornecedoresImportados = importarFornecedoresInputPort.execute(fornecedorMapper.toDomainList(fornecedorRequests));
            return Response.status(Response.Status.CREATED)
                .entity(fornecedorMapper.toResponseList(fornecedoresImportados))
                .build();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id)
    {
        try
        {
            var fornecedor = findFornecedorByIdInputPort.execute(id);
            if (fornecedor == null)
            {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            deleteFornecedorByIdInputPort.execute(id);
            return Response.noContent().build();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
