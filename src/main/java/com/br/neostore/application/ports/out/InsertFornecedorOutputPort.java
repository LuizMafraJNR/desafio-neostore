package com.br.neostore.application.ports.out;

import com.br.neostore.application.core.domain.Fornecedor;

public interface InsertFornecedorOutputPort {
    Fornecedor insert(Fornecedor fornecedor);
}
