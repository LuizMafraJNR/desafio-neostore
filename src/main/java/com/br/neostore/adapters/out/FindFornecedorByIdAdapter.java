package com.br.neostore.adapters.out;

import com.br.neostore.adapters.out.repository.FornecedorRepository;
import com.br.neostore.adapters.out.repository.mapper.FornecedorEntityMapper;
import com.br.neostore.application.core.domain.Fornecedor;
import com.br.neostore.application.ports.out.FindFornecedorByIdOutputPort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class FindFornecedorByIdAdapter implements FindFornecedorByIdOutputPort {

    private final FornecedorRepository fornecedorRepository;
    private final FornecedorEntityMapper fornecedorEntityMapper;

    @Inject
    public FindFornecedorByIdAdapter(FornecedorRepository fornecedorRepository,
        FornecedorEntityMapper fornecedorEntityMapper)
    {
        this.fornecedorRepository = fornecedorRepository;
        this.fornecedorEntityMapper = fornecedorEntityMapper;
    }

    @Override
    public Fornecedor find(Long id) {
        var fornecedorEntity = fornecedorRepository.buscarPorId(id).orElse(null);
        return fornecedorEntityMapper.toDomain(fornecedorEntity);
    }
}
