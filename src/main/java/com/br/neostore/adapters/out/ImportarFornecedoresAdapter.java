package com.br.neostore.adapters.out;

import com.br.neostore.adapters.out.repository.FornecedorRepository;
import com.br.neostore.adapters.out.repository.mapper.FornecedorEntityMapper;
import com.br.neostore.application.core.domain.Fornecedor;
import com.br.neostore.application.exception.BusinessException;
import com.br.neostore.application.ports.out.ImportarFornecedoresOutputPort;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class ImportarFornecedoresAdapter implements ImportarFornecedoresOutputPort
{

	private final FornecedorEntityMapper fornecedorEntityMapper;
	private final FornecedorRepository fornecedorRepository;

	@Inject
	public ImportarFornecedoresAdapter(FornecedorEntityMapper fornecedorEntityMapper,
		FornecedorRepository fornecedorRepository)
	{
		this.fornecedorEntityMapper = fornecedorEntityMapper;
		this.fornecedorRepository = fornecedorRepository;
	}

	@Override
	public List<Fornecedor> importar(List<Fornecedor> fornecedors)
	{
		try
		{
			List<Fornecedor> fornecedoresImportados = new ArrayList<>();
			fornecedors.stream()
				.map(fornecedorEntityMapper::toEntity)
				.map(fornecedorRepository::salvar)
				.map(fornecedorEntityMapper::toDomain)
				.forEach(fornecedoresImportados::add);
			return fornecedoresImportados;
		}
		catch (Exception e)
		{
			throw new BusinessException("Erro ao importar fornecedores.");
		}
	}
}
