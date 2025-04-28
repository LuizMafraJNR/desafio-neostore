package com.br.neostore.adapters.out.repository;

import com.br.neostore.adapters.out.repository.entity.FornecedorEntity;
import java.util.List;
import java.util.Optional;

public interface FornecedorRepository {
    FornecedorEntity salvar(FornecedorEntity fornecedorEntity);
    void deletarPorId(Long id);
    Optional<FornecedorEntity> buscarPorId(Long id);
    List<FornecedorEntity> listar();
    List<FornecedorEntity> listarPaginado(int page, int size);
    long contagem();
}
