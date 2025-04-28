package com.br.neostore.adapters.out;

import com.br.neostore.adapters.out.repository.FornecedorRepository;
import com.br.neostore.adapters.out.repository.mapper.FornecedorEntityMapper;
import com.br.neostore.application.core.domain.Fornecedor;
import com.br.neostore.application.ports.out.InsertFornecedorOutputPort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class InsertFornecedorAdapter implements InsertFornecedorOutputPort {

    private final FornecedorRepository fornecedorRepository;
    private final FornecedorEntityMapper fornecedorEntityMapper;

    @Inject
    public InsertFornecedorAdapter(FornecedorRepository fornecedorRepository,
        FornecedorEntityMapper fornecedorEntityMapper)
    {
        this.fornecedorRepository = fornecedorRepository;
        this.fornecedorEntityMapper = fornecedorEntityMapper;
    }

    @Override
    public Fornecedor insert(Fornecedor fornecedor) {
        var fornecedorEntity = fornecedorEntityMapper.toEntity(fornecedor);
        return fornecedorEntityMapper.toDomain(fornecedorRepository.salvar(fornecedorEntity));
    }
}
