package com.br.neostore.config;

import com.br.neostore.adapters.out.DeleteFornecedorByIdAdapter;
import com.br.neostore.application.core.usecase.fornecedor.DeleteFornecedorByIdUseCase;
import com.br.neostore.application.core.usecase.fornecedor.FindFornecedorByIdUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class DeleteFornecedorByIdConfig {

    @Produces
    public DeleteFornecedorByIdUseCase deleteFornecedorByIdUseCase(FindFornecedorByIdUseCase findFornecedorByIdUseCase,
                                                                   DeleteFornecedorByIdAdapter deleteFornecedorAdapter) {
        return new DeleteFornecedorByIdUseCase(findFornecedorByIdUseCase, deleteFornecedorAdapter);
    }
}
