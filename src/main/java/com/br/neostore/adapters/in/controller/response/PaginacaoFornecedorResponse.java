package com.br.neostore.adapters.in.controller.response;

import java.util.List;
import java.util.Objects;

public class PaginacaoFornecedorResponse<T>
{
	private List<T> conteudo;
	private int paginaAtual;
	private int tamanhoPagina;
	private long totalRegistros;
	private long totalPaginas;

	public PaginacaoFornecedorResponse(List<T> conteudo, int paginaAtual, int tamanhoPagina,
		long totalRegistros, long totalPaginas) {
		this.conteudo = conteudo;
		this.paginaAtual = paginaAtual;
		this.tamanhoPagina = tamanhoPagina;
		this.totalRegistros = totalRegistros;
		this.totalPaginas = totalPaginas;
	}

	public List<T> getConteudo()
	{
		return conteudo;
	}

	public void setConteudo(List<T> conteudo)
	{
		this.conteudo = conteudo;
	}

	public int getPaginaAtual()
	{
		return paginaAtual;
	}

	public void setPaginaAtual(int paginaAtual)
	{
		this.paginaAtual = paginaAtual;
	}

	public int getTamanhoPagina()
	{
		return tamanhoPagina;
	}

	public void setTamanhoPagina(int tamanhoPagina)
	{
		this.tamanhoPagina = tamanhoPagina;
	}

	public long getTotalRegistros()
	{
		return totalRegistros;
	}

	public void setTotalRegistros(long totalRegistros)
	{
		this.totalRegistros = totalRegistros;
	}

	public long getTotalPaginas()
	{
		return totalPaginas;
	}

	public void setTotalPaginas(long totalPaginas)
	{
		this.totalPaginas = totalPaginas;
	}

	@Override
	public boolean equals(Object o)
	{
		if (o == null || getClass() != o.getClass())
		{
			return false;
		}
		PaginacaoFornecedorResponse that = (PaginacaoFornecedorResponse) o;
		return paginaAtual == that.paginaAtual && tamanhoPagina == that.tamanhoPagina
			&& totalRegistros == that.totalRegistros && totalPaginas == that.totalPaginas
			&& Objects.equals(conteudo, that.conteudo);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(conteudo, paginaAtual, tamanhoPagina, totalRegistros, totalPaginas);
	}
}
