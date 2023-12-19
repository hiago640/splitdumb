package br.com.hiago640.splitdumb.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.hiago640.splitdumb.validation.validator.NomeUsuarioValidoValidator;

@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NomeUsuarioValidoValidator.class)
@Documented
public @interface NomeUsuarioValido {
	
	String message() default "";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}