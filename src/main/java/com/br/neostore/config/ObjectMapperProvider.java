package com.br.neostore.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.ws.rs.ext.ContextResolver;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ObjectMapperProvider implements ContextResolver<ObjectMapper>
{

	private final ObjectMapper objectMapper;

	public ObjectMapperProvider()
	{
		objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		// Usado para corrigir um problema que no response do handler de exception, estava retornando um valor estranho
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	}

	@Override
	public ObjectMapper getContext(Class<?> aClass)
	{
		return objectMapper;
	}
}
