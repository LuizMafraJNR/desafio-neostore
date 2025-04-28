package com.br.neostore.application.ports.in;

import com.br.neostore.application.core.domain.Fornecedor;
import java.util.List;

public interface ListarFornecedorInputPort {
    List<Fornecedor> execute();
}
