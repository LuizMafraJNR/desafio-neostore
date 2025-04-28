package com.br.neostore.application.ports.out;

import com.br.neostore.application.core.domain.Fornecedor;
import java.util.List;

public interface ListarFornecedorOutputPort {
    List<Fornecedor> listar();
}
