package com.br.neostore._shared.validation.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {com.br.neostore._shared.validation.validators.CnpjValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCnpj
{
    String message() default "CNPJ invalido";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
