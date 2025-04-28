package com.br.neostore.application.core.usecase.fornecedor;

import com.br.neostore.application.core.domain.Fornecedor;
import com.br.neostore.application.ports.in.ListarFornecedorPaginadoInputPort;
import com.br.neostore.application.ports.out.ListarFornecedorPaginadoOutputPort;
import java.util.List;

public class ListarFornecedorPaginadoUseCase implements ListarFornecedorPaginadoInputPort
{
	private final ListarFornecedorPaginadoOutputPort listarFornecedorPaginadoOutputPorts;

	public ListarFornecedorPaginadoUseCase(ListarFornecedorPaginadoOutputPort listarFornecedorPaginadoOutputPorts)
	{
		this.listarFornecedorPaginadoOutputPorts = listarFornecedorPaginadoOutputPorts;
	}

	@Override
	public List<Fornecedor> listarPaginado(int page, int size)
	{
		int offset = (page - 1) * size;
		return listarFornecedorPaginadoOutputPorts.listarPaginado(offset,size);
	}

	@Override
	public long contar()
	{
		return listarFornecedorPaginadoOutputPorts.contar();
	}
}
