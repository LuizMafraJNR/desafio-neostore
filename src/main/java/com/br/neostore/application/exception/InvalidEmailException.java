package com.br.neostore.application.exception;

public class InvalidEmailException extends RuntimeException
{
	public InvalidEmailException(String message)
	{
		super(message);
	}
}
