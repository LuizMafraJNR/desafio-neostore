package com.br.neostore.adapters.in.exception.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.OffsetDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Problem
{
	private Integer status;
	private String title;
	private String detail;
	private String userMessage;
	private OffsetDateTime dateTime;

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDetail()
	{
		return detail;
	}

	public void setDetail(String detail)
	{
		this.detail = detail;
	}

	public String getUserMessage()
	{
		return userMessage;
	}

	public void setUserMessage(String userMessage)
	{
		this.userMessage = userMessage;
	}

	public OffsetDateTime getDateTime()
	{
		return dateTime;
	}

	public void setDateTime(OffsetDateTime dateTime)
	{
		this.dateTime = dateTime;
	}

	//generete a builder
	public static ProblemBuilder builder() {
		return new ProblemBuilder();
	}
	public static class ProblemBuilder {
		private Integer status;
		private String title;
		private String detail;
		private String userMessage;
		private OffsetDateTime dateTime;

		public ProblemBuilder status(Integer status) {
			this.status = status;
			return this;
		}

		public ProblemBuilder title(String title) {
			this.title = title;
			return this;
		}

		public ProblemBuilder detail(String detail) {
			this.detail = detail;
			return this;
		}

		public ProblemBuilder userMessage(String userMessage) {
			this.userMessage = userMessage;
			return this;
		}

		public ProblemBuilder dateTime(OffsetDateTime dateTime) {
			this.dateTime = dateTime;
			return this;
		}

		public Problem build() {
			Problem problem = new Problem();
			problem.status = this.status;
			problem.title = this.title;
			problem.detail = this.detail;
			problem.userMessage = this.userMessage;
			problem.dateTime = this.dateTime;
			return problem;
		}
	}
}
