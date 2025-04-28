package com.br.neostore.adapters.out;

import com.br.neostore.adapters.out.repository.FornecedorRepository;
import com.br.neostore.adapters.out.repository.mapper.FornecedorEntityMapper;
import com.br.neostore.application.core.domain.Fornecedor;
import com.br.neostore.application.ports.out.ListarFornecedorPaginadoOutputPort;
import jakarta.inject.Inject;
import java.util.List;

public class ListarFornecedorPaginadoAdapter implements ListarFornecedorPaginadoOutputPort
{
	private final FornecedorRepository fornecedorRepository;
	private final FornecedorEntityMapper fornecedorEntityMapper;

	@Inject
	public ListarFornecedorPaginadoAdapter(FornecedorRepository fornecedorRepository,
		FornecedorEntityMapper fornecedorEntityMapper)
	{
		this.fornecedorRepository = fornecedorRepository;
		this.fornecedorEntityMapper = fornecedorEntityMapper;
	}

	@Override
	public List<Fornecedor> listarPaginado(int page, int size)
	{
		return fornecedorEntityMapper.toDomainList(fornecedorRepository.listarPaginado(page,size));
	}

	@Override
	public long contar()
	{
		return fornecedorRepository.contagem();
	}
}
