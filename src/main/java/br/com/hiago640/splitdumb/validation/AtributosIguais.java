package br.com.hiago640.splitdumb.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.hiago640.splitdumb.validation.validator.AtributosIguaisValidator;

@Constraint(validatedBy = AtributosIguaisValidator.class)
@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AtributosIguais {
	String message() default "Os valores são diferentes";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
	String primeiro();
	String segundo();

	@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List
	{
		AtributosIguais[] value();
	}
}