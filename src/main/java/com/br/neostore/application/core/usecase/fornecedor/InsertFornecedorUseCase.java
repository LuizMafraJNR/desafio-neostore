package com.br.neostore.application.core.usecase.fornecedor;

import com.br.neostore.application.core.domain.Fornecedor;
import com.br.neostore.application.ports.in.InsertFornecedorInputPort;
import com.br.neostore.application.ports.out.InsertFornecedorOutputPort;


public class InsertFornecedorUseCase implements InsertFornecedorInputPort {

    private final InsertFornecedorOutputPort insertFornecedorOutputPort;

    public InsertFornecedorUseCase(InsertFornecedorOutputPort insertFornecedorOutputPort) {;
        this.insertFornecedorOutputPort = insertFornecedorOutputPort;
    }

    @Override
    public Fornecedor execute(Fornecedor fornecedor) {
        return insertFornecedorOutputPort.insert(fornecedor);
    }
}
