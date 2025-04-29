package com.br.neostore.adapters.in.controller.request;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.br.neostore.application.core.domain.Fornecedor;
import com.br.neostore.application.exception.InvalidCnpjException;
import com.br.neostore.application.exception.InvalidEmailException;
import org.junit.jupiter.api.Test;

public class FornecedorRequestTest {

    @Test
    void testEmailInvalido() {
        assertThrows(InvalidEmailException.class, () -> {
            new Fornecedor("Fornecedor Teste", "email_invalido", "Descrição válida", "12.345.678/0001-22");
        });
    }

    @Test
    void testCnpjInvalido() {
        assertThrows(InvalidCnpjException.class, () -> {
            new Fornecedor("Fornecedor Teste", "email@valido.com", "Descrição válida", "123456789");
        });
    }

    @Test
    void testFornecedorValido() {
        Fornecedor fornecedor = new Fornecedor("Fornecedor Teste", "email@valido.com", "Descrição válida", "12.345.678/0001-22");
    }
}