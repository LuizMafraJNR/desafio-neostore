package com.br.neostore.adapters.in.controller.mapper;

import com.br.neostore.adapters.in.controller.request.FornecedorRequest;
import com.br.neostore.adapters.in.controller.response.FornecedorResponse;
import com.br.neostore.application.core.domain.Fornecedor;
import java.util.ArrayList;
import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface FornecedorMapper
{
	Fornecedor toDomain(FornecedorRequest fornecedorRequest);
	FornecedorResponse toResponse(Fornecedor fornecedor);

	@IterableMapping(elementTargetType = Fornecedor.class)
	default List<Fornecedor> toDomainList(List<FornecedorRequest> fornecedorRequests)
	{
		List<Fornecedor> list = new ArrayList<>(fornecedorRequests.size());
		for (FornecedorRequest fornecedorRequest : fornecedorRequests)
		{
			list.add(toDomain(fornecedorRequest));
		}
		return list;
	}

	@IterableMapping(elementTargetType = FornecedorResponse.class)
	default List<FornecedorResponse> toResponseList(List<Fornecedor> fornecedores)
	{
		List<FornecedorResponse> list = new ArrayList<>(fornecedores.size());
		for (Fornecedor fornecedor : fornecedores)
		{
			list.add(toResponse(fornecedor));
		}
		return list;
	}
}
