package com.br.neostore.application.core.usecase.fornecedor;

import com.br.neostore.application.core.domain.Fornecedor;
import com.br.neostore.application.exception.SupplierNotFoundException;
import com.br.neostore.application.ports.in.FindFornecedorByIdInputPort;
import com.br.neostore.application.ports.in.UpdateFornecedorByIdInputPort;
import com.br.neostore.application.ports.out.UpdateFornecedorByIdOutputPort;

public class UpdateFornecedorByIdUseCase implements UpdateFornecedorByIdInputPort {

    private FindFornecedorByIdInputPort findFornecedorByIdInputPort;
    private UpdateFornecedorByIdOutputPort updateFornecedorByIdOutputPort;

    public UpdateFornecedorByIdUseCase(FindFornecedorByIdInputPort findFornecedorByIdInputPort,
                                       UpdateFornecedorByIdOutputPort updateFornecedorByIdOutputPort) {
        this.findFornecedorByIdInputPort = findFornecedorByIdInputPort;
        this.updateFornecedorByIdOutputPort = updateFornecedorByIdOutputPort;
    }

    @Override
    public Fornecedor execute(Long id, Fornecedor fornecedor) {
        var fornecedorFound = findFornecedorByIdInputPort.execute(id);
        fornecedor.setId(id);
        if (fornecedorFound == null) {
            throw new SupplierNotFoundException("Fornecedor não encontrado");
        }
        return updateFornecedorByIdOutputPort.update(fornecedor);
    }
}
