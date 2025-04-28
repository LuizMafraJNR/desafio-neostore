package com.br.neostore.application.core.usecase.fornecedor;

import com.br.neostore.application.core.domain.Fornecedor;
import com.br.neostore.application.ports.in.FindFornecedorByIdInputPort;
import com.br.neostore.application.ports.out.FindFornecedorByIdOutputPort;

public class FindFornecedorByIdUseCase implements FindFornecedorByIdInputPort {

    public final FindFornecedorByIdOutputPort findFornecedorByIdOutputPort;

    public FindFornecedorByIdUseCase(FindFornecedorByIdOutputPort findFornecedorByIdOutputPort) {
        this.findFornecedorByIdOutputPort = findFornecedorByIdOutputPort;
    }
    @Override
    public Fornecedor execute(Long id) {
        return findFornecedorByIdOutputPort.find(id);
    }
}
