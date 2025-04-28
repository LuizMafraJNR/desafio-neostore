package com.br.neostore.application.ports.out;

import com.br.neostore.application.core.domain.Fornecedor;
import java.util.List;

public interface ListarFornecedorPaginadoOutputPort
{
	List<Fornecedor> listarPaginado(int offset, int size);

	long contar();
}
