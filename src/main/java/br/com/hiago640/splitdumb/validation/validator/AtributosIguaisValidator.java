package br.com.hiago640.splitdumb.validation.validator;

import java.lang.reflect.Field;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.hiago640.splitdumb.validation.AtributosIguais;

public class AtributosIguaisValidator implements ConstraintValidator<AtributosIguais, Object> {

	private String firstFieldName;
	private String secondFieldName;
	private String message;

	@Override
	public void initialize(final AtributosIguais constraintAnnotation) {
		firstFieldName = constraintAnnotation.primeiro();
		secondFieldName = constraintAnnotation.segundo();
		message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(final Object value, final ConstraintValidatorContext context) {
		boolean valid = true;
		try {
			Field field = value.getClass().getDeclaredField(firstFieldName);
			field.setAccessible(true);
			final Object firstObj = field.get(value);
			field = value.getClass().getDeclaredField(secondFieldName);
			field.setAccessible(true);
			final Object secondObj = field.get(value);
			
//			import org.springframework.beans.BeanWrapperImpl;
//			final Object firstObj = new BeanWrapperImpl(value).getPropertyValue(firstFieldName);
//			final Object secondObj = new BeanWrapperImpl(value).getPropertyValue(secondFieldName);

			valid =  firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
		} catch (final Exception ignore) {
			// we can ignore
		}

		if (!valid){
			context.buildConstraintViolationWithTemplate(message)
				.addPropertyNode(firstFieldName)
				.addConstraintViolation()
				.disableDefaultConstraintViolation();
		}

		return valid;
	}
}