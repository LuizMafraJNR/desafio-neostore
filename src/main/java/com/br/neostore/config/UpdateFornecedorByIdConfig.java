package com.br.neostore.config;

import com.br.neostore.adapters.out.UpdateFornecedorByIdAdapter;
import com.br.neostore.application.core.usecase.fornecedor.FindFornecedorByIdUseCase;
import com.br.neostore.application.core.usecase.fornecedor.UpdateFornecedorByIdUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class UpdateFornecedorByIdConfig {
    @Produces
    public UpdateFornecedorByIdUseCase updateFornecedorByIdUseCase(FindFornecedorByIdUseCase findFornecedorByIdUseCase,
                                                                   UpdateFornecedorByIdAdapter updateFornecedorAdapter) {
        return new UpdateFornecedorByIdUseCase(findFornecedorByIdUseCase, updateFornecedorAdapter);
    }
}
