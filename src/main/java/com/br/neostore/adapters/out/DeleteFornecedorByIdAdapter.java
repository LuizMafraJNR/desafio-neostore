package com.br.neostore.adapters.out;

import com.br.neostore.adapters.out.repository.FornecedorRepository;
import com.br.neostore.application.ports.out.DeleteFornecedorByIdOutputPort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DeleteFornecedorByIdAdapter implements DeleteFornecedorByIdOutputPort {

    private final FornecedorRepository fornecedorRepository;

    @Inject
    public DeleteFornecedorByIdAdapter(FornecedorRepository fornecedorRepository)
    {
        this.fornecedorRepository = fornecedorRepository;
    }

    @Override
    public void delete(Long id) {
        fornecedorRepository.deletarPorId(id);
    }
}
