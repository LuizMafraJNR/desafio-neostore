package com.br.neostore.application.core.usecase.importador;

import com.br.neostore.application.core.domain.Fornecedor;
import com.br.neostore.application.ports.in.ImportarFornecedoresInputPort;
import com.br.neostore.application.ports.out.ImportarFornecedoresOutputPort;
import java.util.List;

public class ImportarFornecedoresUseCase implements ImportarFornecedoresInputPort
{

	private final ImportarFornecedoresOutputPort importarFornecedoresOutputPort;

	public ImportarFornecedoresUseCase(ImportarFornecedoresOutputPort importarFornecedoresOutputPort)
	{
		this.importarFornecedoresOutputPort = importarFornecedoresOutputPort;
	}

	@Override
	public List<Fornecedor> execute(List<Fornecedor> fornecedors)
	{
		return importarFornecedoresOutputPort.importar(fornecedors);
	}
}
