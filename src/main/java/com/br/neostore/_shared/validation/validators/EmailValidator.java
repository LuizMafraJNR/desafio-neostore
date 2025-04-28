package com.br.neostore._shared.validation.validators;

import com.br.neostore._shared.validation.annotations.ValidEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Objects;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<ValidEmail, String>
{
    private static final String REGEX_PATTERN = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext)
    {
        if (Objects.isNull(s) || s.isEmpty()) return false;
        return Pattern.compile(REGEX_PATTERN).matcher(s).matches();
    }
}
