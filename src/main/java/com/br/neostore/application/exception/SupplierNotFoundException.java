package com.br.neostore.application.exception;

public class SupplierNotFoundException extends EntityNotFoundException
{
	public SupplierNotFoundException(String message)
	{
		super(message);
	}

	public SupplierNotFoundException(Long id)
	{
		super("Fornecedor não encontrado com o id: " + id);
	}
}
