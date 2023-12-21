package br.edu.ifpb.pweb2.sisyphus.validator;

import java.lang.annotation.*;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OnlyNumberMatConstraintValidador.class)
public @interface OnlyNumberMat {

    String message() default "A matrícula deve conter apenas números.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}