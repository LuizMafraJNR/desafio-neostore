package com.br.neostore.application.core.usecase.fornecedor;

import com.br.neostore.application.ports.in.DeleteFornecedorByIdInputPort;
import com.br.neostore.application.ports.in.FindFornecedorByIdInputPort;
import com.br.neostore.application.ports.out.DeleteFornecedorByIdOutputPort;
import java.util.Objects;

public class DeleteFornecedorByIdUseCase implements DeleteFornecedorByIdInputPort {

    private FindFornecedorByIdInputPort findFornecedorByIdInputPort;
    private DeleteFornecedorByIdOutputPort deleteFornecedorByIdOutputPort;

    public DeleteFornecedorByIdUseCase(FindFornecedorByIdInputPort findFornecedorByIdInputPort,
                                       DeleteFornecedorByIdOutputPort deleteFornecedorByIdOutputPort) {
        this.findFornecedorByIdInputPort = findFornecedorByIdInputPort;
        this.deleteFornecedorByIdOutputPort = deleteFornecedorByIdOutputPort;
    }

    @Override
    public void execute(Long id) {
        if (Objects.nonNull(findFornecedorByIdInputPort.execute(id))) {
            deleteFornecedorByIdOutputPort.delete(id);
        } else {
            throw new IllegalArgumentException("Fornecedor não encontrado");
        }
    }
}
