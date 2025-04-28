package com.br.neostore.config;

import com.br.neostore.adapters.out.ImportarFornecedoresAdapter;
import com.br.neostore.application.core.usecase.importador.ImportarFornecedoresUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class ImportarFornecedoresConfig
{
	@Produces
	public ImportarFornecedoresUseCase importarFornecedoresUseCase(ImportarFornecedoresAdapter importarFornecedoresAdapter){
		return new ImportarFornecedoresUseCase(importarFornecedoresAdapter);
	}
}
