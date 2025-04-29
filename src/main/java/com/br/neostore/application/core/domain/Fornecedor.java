package com.br.neostore.application.core.domain;

import com.br.neostore.application.exception.InvalidCnpjException;
import com.br.neostore.application.exception.InvalidEmailException;
import java.util.Objects;
import java.util.regex.Pattern;

public class Fornecedor {
    private Long id;
    private String name;
    private String email;
    private String description;
    private String cnpj;

    public Fornecedor(String name, String email, String description, String cnpj) {
        if (!Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$").matcher(email).matches()) {
            throw new InvalidEmailException("Email Inválido");
        }
        if (cnpj == null || cnpj.isEmpty() || (cnpj.length() < 14 || cnpj.length() > 18)) throw new InvalidCnpjException("CNPJ Inválido");
        this.name = name;
        this.email = email;
        this.description = description;
        this.cnpj = cnpj;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        Fornecedor that = (Fornecedor) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(description, that.description) && Objects.equals(cnpj, that.cnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, description, cnpj);
    }
}
