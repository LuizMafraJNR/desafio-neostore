package com.br.neostore.config;

import com.br.neostore.adapters.out.InsertFornecedorAdapter;
import com.br.neostore.application.core.usecase.fornecedor.InsertFornecedorUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;


@ApplicationScoped
public class InsertFornecedorConfig {

    @Produces
    public InsertFornecedorUseCase insertFornecedorUseCase(InsertFornecedorAdapter insertFornecedorAdapter) {
         return new InsertFornecedorUseCase(insertFornecedorAdapter);
    }
}
