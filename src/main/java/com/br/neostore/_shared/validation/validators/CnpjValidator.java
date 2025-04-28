package com.br.neostore._shared.validation.validators;

import com.br.neostore._shared.validation.annotations.ValidCnpj;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Objects;

public class CnpjValidator implements ConstraintValidator<ValidCnpj, String>
{
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext)
    {
        if (Objects.isNull(s) || s.isEmpty()) return false;
        // "([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})" Usar no front
        String cnpj = s.replaceAll("\\D", "");
        return cnpj.length() == 14;
    }
}