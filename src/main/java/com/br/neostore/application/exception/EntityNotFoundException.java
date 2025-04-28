package com.br.neostore.application.exception;

import java.io.Serial;

public  abstract class EntityNotFoundException extends BusinessException
{
	@Serial
	private static final long serialVersionUID = 1L;

	protected EntityNotFoundException(String message)
	{
		super(message);
	}
}
