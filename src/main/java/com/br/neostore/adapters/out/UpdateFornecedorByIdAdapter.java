package com.br.neostore.adapters.out;

import com.br.neostore.adapters.out.repository.FornecedorRepository;
import com.br.neostore.adapters.out.repository.mapper.FornecedorEntityMapper;
import com.br.neostore.application.core.domain.Fornecedor;
import com.br.neostore.application.ports.out.UpdateFornecedorByIdOutputPort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UpdateFornecedorByIdAdapter implements UpdateFornecedorByIdOutputPort {

    private final FornecedorEntityMapper fornecedorEntityMapper;
    private final FornecedorRepository fornecedorRepository;

    @Inject
    public UpdateFornecedorByIdAdapter(FornecedorEntityMapper fornecedorEntityMapper,
        FornecedorRepository fornecedorRepository)
    {
        this.fornecedorEntityMapper = fornecedorEntityMapper;
        this.fornecedorRepository = fornecedorRepository;
    }

    @Override
    public Fornecedor update(Fornecedor fornecedor) {
        var fornecedorEntity = fornecedorEntityMapper.toEntity(fornecedor);
        return fornecedorEntityMapper.toDomain(fornecedorRepository.salvar(fornecedorEntity));
    }
}
