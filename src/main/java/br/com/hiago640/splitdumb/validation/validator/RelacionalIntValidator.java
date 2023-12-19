package br.com.hiago640.splitdumb.validation.validator;

import java.lang.reflect.Field;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.hiago640.splitdumb.validation.RelacionalInt;
import br.com.hiago640.splitdumb.validation.RelacionalInt.Relacao;

public class RelacionalIntValidator implements ConstraintValidator<RelacionalInt, Object> {

	private String atributo1;
	private String atributo2;
	private Relacao relacao;

	@Override
	public void initialize(final RelacionalInt annotation) {
		atributo1 = annotation.atributo1();
		atributo2 = annotation.atributo2();
		relacao = annotation.relacao();
	}

	@Override
	public boolean isValid(final Object value, final ConstraintValidatorContext ctx) {
		if (value == null) {
			return true;
		}
		try {
			Field field1 = value.getClass().getDeclaredField(atributo1);
			field1.setAccessible(true);
			final int valor1 = (int) field1.get(value);
			Field field2 = value.getClass().getDeclaredField(atributo2);
			field2.setAccessible(true);
			final int valor2 = (int) field2.get(value);
			
			String mensagem = "";
			boolean valido = false;
			switch (relacao) {
				case IGUAL: valido = valor1 == valor2;
				            if (!valido) mensagem = "Os valores são diferentes"; break;
				case DIFERENTE: valido = valor1 != valor2; 
								if (!valido) mensagem = "Os valores são iguais"; break;
				case MAIOR: valido = valor1 > valor2; 
							if (!valido) mensagem = "O " + atributo1 + " não é maior que " + atributo2; break;
				case MENOR: valido = valor1 < valor2; 
							if (!valido) mensagem = "O " + atributo1 + " não é menor que " + atributo2; break;
				case MAIORIGUAL: valido = valor1 >= valor2; 
								 if (!valido) mensagem = "O " + atributo1 + " não é maior ou igual a " + atributo2; break;
				case MENORIGUAL: valido = valor1 <= valor2; 
								 if (!valido) mensagem = "O " + atributo1 + " não é menor ou igual a " + atributo2; break;
			}

			if (!valido) {
				ctx.buildConstraintViolationWithTemplate(mensagem)
				.addConstraintViolation()
				.disableDefaultConstraintViolation();
			}
			return valido;
		} catch (final Exception ignore) {
			// we can ignore
		}
		return true;
	}

}
