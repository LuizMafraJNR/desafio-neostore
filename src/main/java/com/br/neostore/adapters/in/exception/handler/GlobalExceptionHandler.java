package com.br.neostore.adapters.in.exception.handler;

import com.br.neostore.application.exception.BusinessException;
import com.br.neostore.application.exception.InvalidCnpjException;
import com.br.neostore.application.exception.InvalidEmailException;
import com.br.neostore.application.exception.SupplierNotFoundException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import java.security.InvalidParameterException;
import java.time.OffsetDateTime;
import java.util.Objects;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Throwable>
{
	public static final String MSG_ERRO_GENERICO_USER_FINAL = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se o problema persistir, entre em contato com o administrador do sistema.";

	@Override
	public Response toResponse(Throwable throwable)
	{
		Throwable cause = throwable.getCause() != null ? throwable.getCause() : throwable;
		if (cause instanceof ConstraintViolationException)
		{
			return handleConstraintViolationException((ConstraintViolationException) throwable);
		}
		else if (cause instanceof UnrecognizedPropertyException)
		{
			return handleUnrecognizedPropertyException((UnrecognizedPropertyException) throwable);
		}
		else if (cause instanceof InvalidParameterException)
		{
			return handleInvalidParameterException((InvalidParameterException) throwable);
		}
		else if (cause instanceof NotFoundException)
		{
			return handleNotFoundException((NotFoundException) throwable);
		}
		else if (cause instanceof InvalidEmailException)
		{
			Problem problem = createProblemBuilder("Campo Inválido", cause.getMessage(), 400)
				.build();
			return handleExceptionInternal(throwable, problem, 400);
		}
		else if (cause instanceof InvalidCnpjException)
		{
			Problem problem = createProblemBuilder("Campo Inválido", cause.getMessage(), 400)
				.build();
			return handleExceptionInternal(throwable, problem, 400);
		}
		else if (cause instanceof SupplierNotFoundException)
		{
			Problem problem = createProblemBuilder("Entidade Não Encontrada", cause.getMessage(), 404)
				.build();
			return handleExceptionInternal(throwable, problem, 404);
		}
		else if (cause instanceof BusinessException)
		{
			Problem problem = createProblemBuilder("Violação de Regra de Negócio", cause.getMessage(), 400)
				.build();
			return handleExceptionInternal(throwable, problem, 400);
		}
		else
		{
			return handleInternalServerError(throwable);
		}
	}

	private Response handleUnrecognizedPropertyException(UnrecognizedPropertyException ex)
	{
		Problem problem = Problem.builder()
			.status(Response.Status.BAD_REQUEST.getStatusCode())
			.title("Propriedade  Não Reconhecida")
			.detail("Parece que alguma propriedade enviada não é esperada no corpo da requisição.")
			.userMessage("A propriedade '" + ex.getPropertyName() + "' não é reconhecida.")
			.dateTime(OffsetDateTime.now())
			.build();
		return Response.status(Response.Status.BAD_REQUEST).entity(problem).build();
	}

	private Response handleExceptionInternal(Throwable throwable, Object body, int status)
	{
		if (Objects.isNull(body))
		{
			body = Problem.builder()
				.status(status)
				.title("Erro Interno no Servidor")
				.detail(throwable.getMessage())
				.userMessage(MSG_ERRO_GENERICO_USER_FINAL)
				.dateTime(OffsetDateTime.now())
				.build();
		}
		else if (body instanceof String)
		{
			body = Problem.builder()
				.status(status)
				.title((String) body)
				.userMessage(MSG_ERRO_GENERICO_USER_FINAL)
				.dateTime(OffsetDateTime.now())
				.build();
		}
		return Response.status(Response.Status.fromStatusCode(status)).entity(body).build();
	}

	private Response handleConstraintViolationException(ConstraintViolationException ex) {
		Problem problem = Problem.builder()
			.status(Response.Status.BAD_REQUEST.getStatusCode())
			.title("Violação de Restrições")
			.detail(ex.getMessage())
			.userMessage("Um ou mais campos são inválidos. Por favor, corrija e tente novamente.")
			.dateTime(OffsetDateTime.now())
			.build();
		return Response.status(Response.Status.BAD_REQUEST).entity(problem).build();
	}

	private Response handleInvalidParameterException(InvalidParameterException ex) {
		Problem problem = Problem.builder()
			.status(Response.Status.BAD_REQUEST.getStatusCode())
			.title("Parâmetro Inválido")
			.detail(ex.getMessage())
			.userMessage("O parâmetro fornecido é inválido. Por favor, verifique e tente novamente.")
			.dateTime(OffsetDateTime.now())
			.build();
		return Response.status(Response.Status.BAD_REQUEST).entity(problem).build();
	}

	private Response handleNotFoundException(NotFoundException ex) {
		Problem problem = Problem.builder()
			.status(Response.Status.NOT_FOUND.getStatusCode())
			.title("Recurso Não Encontrado")
			.detail(ex.getMessage())
			.userMessage("O recurso solicitado não foi encontrado.")
			.dateTime(OffsetDateTime.now())
			.build();
		return Response.status(Response.Status.NOT_FOUND).entity(problem).build();
	}

	private Response handleInternalServerError(Throwable ex) {
		Problem problem = Problem.builder()
			.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode())
			.title("Erro Interno no Servidor")
			.detail(ex.getMessage())
			.userMessage("Ocorreu um erro inesperado. Por favor, tente novamente mais tarde.")
			.dateTime(OffsetDateTime.now())
			.build();
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(problem).build();
	}

	private Problem.ProblemBuilder createProblemBuilder(String problemType, String detail, Integer status)
	{
		return Problem.builder()
			.status(status)
			.title(problemType)
			.detail(detail)
			.dateTime(OffsetDateTime.now());
	}
}
