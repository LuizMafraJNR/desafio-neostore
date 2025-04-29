package com.br.neostore.application.exception;

public class InvalidCnpjException extends RuntimeException
{
	public InvalidCnpjException(String message)
	{
		super(message);
	}
}
