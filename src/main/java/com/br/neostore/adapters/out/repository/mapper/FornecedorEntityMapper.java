package com.br.neostore.adapters.out.repository.mapper;

import com.br.neostore.adapters.out.repository.entity.FornecedorEntity;
import com.br.neostore.application.core.domain.Fornecedor;
import java.util.ArrayList;
import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;


@Mapper(componentModel = "cdi")
public interface FornecedorEntityMapper
{
	FornecedorEntity toEntity(Fornecedor fornecedor);
	Fornecedor toDomain(FornecedorEntity fornecedorEntity);

	@IterableMapping(elementTargetType = FornecedorEntity.class)
	default List<FornecedorEntity> toEntityList(List<Fornecedor> fornecedores)
	{
		List<FornecedorEntity> list = new ArrayList<>(fornecedores.size());
		for (Fornecedor fornecedor : fornecedores)
		{
			list.add(toEntity(fornecedor));
		}
		return list;
	}

	@IterableMapping(elementTargetType = Fornecedor.class)
	default List<Fornecedor> toDomainList(List<FornecedorEntity> fornecedorEntities)
	{
		List<Fornecedor> list = new ArrayList<>(fornecedorEntities.size());
		for (FornecedorEntity fornecedorEntity : fornecedorEntities)
		{
			list.add(toDomain(fornecedorEntity));
		}
		return list;
	}
}

