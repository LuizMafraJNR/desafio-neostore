package com.br.neostore.application.core.usecase.fornecedor;

import com.br.neostore.application.core.domain.Fornecedor;
import com.br.neostore.application.ports.in.ListarFornecedorInputPort;
import com.br.neostore.application.ports.out.ListarFornecedorOutputPort;
import java.util.List;

public class ListarFornecedorUseCase implements ListarFornecedorInputPort {

    private final ListarFornecedorOutputPort listarFornecedorOutputPort;

    public ListarFornecedorUseCase(ListarFornecedorOutputPort listarFornecedorOutputPort) {
        this.listarFornecedorOutputPort = listarFornecedorOutputPort;
    }

    @Override
    public List<Fornecedor> execute() {
        return listarFornecedorOutputPort.listar();
    }
}
