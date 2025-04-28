package com.br.neostore.config;

import com.br.neostore.adapters.out.ListarFornecedorAdapter;
import com.br.neostore.application.core.usecase.fornecedor.ListarFornecedorUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class ListarFornecedorConfig {

    @Produces
    public ListarFornecedorUseCase listarFornecedorUseCase(
        ListarFornecedorAdapter listarFornecedorAdapter) {
        return new ListarFornecedorUseCase(listarFornecedorAdapter);

    }
}
