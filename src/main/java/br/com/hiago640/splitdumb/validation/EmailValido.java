package br.com.hiago640.splitdumb.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.hiago640.splitdumb.validation.validator.EmailValidoValidator;

@Constraint(validatedBy = EmailValidoValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Documented
public @interface EmailValido {

	public String message() default "O formato do e-mail não é válido";

	public Class<?>[] groups() default {};

	public Class<? extends Payload>[] payload() default{};

}