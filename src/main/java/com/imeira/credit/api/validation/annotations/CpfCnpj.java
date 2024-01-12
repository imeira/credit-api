package com.imeira.credit.api.validation.annotations;


import com.imeira.credit.api.validation.CpfCnpjValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CpfCnpjValidator.class)
@Documented
public @interface CpfCnpj {

    String message() default "must be a well-formed CPF or CPNJ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
