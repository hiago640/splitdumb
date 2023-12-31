package br.com.hiago640.splitdumb.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.hiago640.splitdumb.validation.EmailValido;

/*
	<!-- Adiciona a dependência do javax.mail para fazer validação -->
		<!-- São 659kb -->
		<dependency>
			<groupId>com.sun.mail</groupId>
			<artifactId>javax.mail</artifactId>
			<version>1.6.2</version>
		</dependency>
		
		<!-- Adiciona a dependência do Apache Commons Validator -->
		<!-- São 190kb -->
		<dependency>
			<groupId>commons-validator</groupId>
			<artifactId>commons-validator</artifactId>
			<version>1.7</version>
		</dependency>
 */

public class EmailValidoValidator implements ConstraintValidator<EmailValido, String> {

	private static final Logger logger = LoggerFactory.getLogger(EmailValidoValidator.class);

//	Usando java.mail
//	@Override
//	public boolean isValid(String value, ConstraintValidatorContext context) {
//		if (value == null) {
//			return false;
//		}
//		try {
//			InternetAddress endereco = new InternetAddress(value);
//			endereco.validate();
//			logger.debug("O e-mail {} foi considerado válido", value);
//			return true;
//		} catch (AddressException e) {
//			logger.debug("O e-mail {} foi considerado inválido", value);
//			return false;
//		}
//	}

	// Usando commons.validator
	private static final EmailValidator validator = EmailValidator.getInstance();
	// private static final EmailValidator validator =
	// EmailValidator.getInstance(true);
	// private static final EmailValidator validator =
	// EmailValidator.getInstance(true, true);

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		if (email != null && !email.isBlank()) {
			boolean resultado = validator.isValid(email);
			logger.debug("O e-mail {} foi considerado {}", email, resultado ? "válido" : "inválido");
			return resultado;
		}
		return true;
	}

}
