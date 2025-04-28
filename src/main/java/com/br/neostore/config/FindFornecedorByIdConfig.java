package com.br.neostore.config;

import com.br.neostore.adapters.out.FindFornecedorByIdAdapter;
import com.br.neostore.application.core.usecase.fornecedor.FindFornecedorByIdUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class FindFornecedorByIdConfig {

    @Produces
    public FindFornecedorByIdUseCase findFornecedorByIdUseCase(FindFornecedorByIdAdapter findFornecedorByIdAdapter) {
        return new FindFornecedorByIdUseCase(findFornecedorByIdAdapter);
    }
}
