package br.com.hiago640.splitdumb.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.hiago640.splitdumb.validation.validator.EmailUnicoValidator;

@Constraint(validatedBy = EmailUnicoValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Documented
public @interface EmailUnico {

	public String message() default "Já existe um contato com esse e-mail";

	public Class<?>[] groups() default {};

	public Class<? extends Payload>[] payload() default{};

}