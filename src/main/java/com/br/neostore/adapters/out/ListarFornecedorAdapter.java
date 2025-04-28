package com.br.neostore.adapters.out;

import com.br.neostore.adapters.out.repository.FornecedorRepository;
import com.br.neostore.adapters.out.repository.mapper.FornecedorEntityMapper;
import com.br.neostore.application.core.domain.Fornecedor;
import com.br.neostore.application.ports.out.ListarFornecedorOutputPort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ListarFornecedorAdapter implements ListarFornecedorOutputPort {

    private final FornecedorRepository fornecedorRepository;
    private final FornecedorEntityMapper fornecedorEntityMapper;

    @Inject
    public ListarFornecedorAdapter(FornecedorRepository fornecedorRepository,
        FornecedorEntityMapper fornecedorEntityMapper)
    {
        this.fornecedorRepository = fornecedorRepository;
        this.fornecedorEntityMapper = fornecedorEntityMapper;
    }

    @Override
    public List<Fornecedor> listar() {
        var fornecedorEntityList = fornecedorRepository.listar();
        return fornecedorEntityList.stream()
                .map(fornecedorEntityMapper::toDomain)
                .toList();
    }
}
