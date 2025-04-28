package com.br.neostore.application.ports.in;

import com.br.neostore.application.core.domain.Fornecedor;

public interface UpdateFornecedorByIdInputPort {
    Fornecedor execute(Long id, Fornecedor fornecedor);
}
