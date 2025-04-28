package com.br.neostore.adapters.in.controller.request;

import jakarta.validation.constraints.NotBlank;
import java.util.Objects;

public class FornecedorRequest
{
    private Long id;
    @NotBlank
    private String name;
    private String email;
    @NotBlank
    private String description;
    private String cnpj;

    public FornecedorRequest() {
    }

    public FornecedorRequest(Long id, String name, String email, String description, String cnpj)
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.description = description;
        this.cnpj = cnpj;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FornecedorRequest that = (FornecedorRequest) o;
        return Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(description, that.description) && Objects.equals(cnpj, that.cnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, description, cnpj);
    }
}