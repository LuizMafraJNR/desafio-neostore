package com.br.neostore.config;

import com.br.neostore.adapters.out.ListarFornecedorPaginadoAdapter;
import com.br.neostore.application.core.usecase.fornecedor.ListarFornecedorPaginadoUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class ListarFornecedorPaginadoConfig
{
	@Produces
	public ListarFornecedorPaginadoUseCase listarFornecedorPaginadoUseCase(
		ListarFornecedorPaginadoAdapter listarFornecedorPaginadoAdapter)
	{
		return new ListarFornecedorPaginadoUseCase(listarFornecedorPaginadoAdapter);
	}
}
