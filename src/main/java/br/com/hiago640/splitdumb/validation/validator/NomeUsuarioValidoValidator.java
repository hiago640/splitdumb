package br.com.hiago640.splitdumb.validation.validator;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.hiago640.splitdumb.validation.NomeUsuarioValido;

//pelo menos uma letra
//pelo menos um digito
//pelo menos 1 caractere especial
//tamanho mínimo 6
//tamanho máximo 20
//pelo menos 1 caractere maiúsculo
//pelo menos 1 caractere minúsculo
//não pode ser um nome de usuário proibido
public class NomeUsuarioValidoValidator implements ConstraintValidator<NomeUsuarioValido, String> {

	private static final List<String> LISTA_CARACTERES_ESPECIAIS = Arrays.asList("!", "@", "#", "$", "%", "&", "*", ".",
			",", "~", "^", "/", "\\", "+", ":", ";", "=", "'", "`", "[", "]", "(", ")", "{", "}", "<", ">", "-", "_");
	private static final List<String> LISTA_NOMES_USUARIO_PROIBIDOS = Arrays.asList("root", "eu", "admin");
	private String mensagemFinal = "";

	@Override
	public boolean isValid(String nomeUsuario, ConstraintValidatorContext constraintValidatorContext) {
		mensagemFinal = "";

		if (nomeUsuario != null) {
			boolean valido = validarPadrao(nomeUsuario, "[a-zA-Z]", "Ao menos uma letra");
			valido &= validarPadrao(nomeUsuario, "\\d", "Ao menos um dígito");
			valido &= validarCaractereEspecial(nomeUsuario);
			valido &= validarTamanho(nomeUsuario, 5, 20,
					"Tamanho mínimo de 5 caracteres; Tamanho máximo de 20 caracteres");
			valido &= validarPadrao(nomeUsuario, "[A-Z]", "Ao menos um caractere maiúsculo");
			valido &= validarPadrao(nomeUsuario, "[a-z]", "Ao menos um caractere minúsculo");
			valido &= !LISTA_NOMES_USUARIO_PROIBIDOS.contains(nomeUsuario.toLowerCase());

			if (!valido) {
				constraintValidatorContext.buildConstraintViolationWithTemplate(mensagemFinal).addConstraintViolation()
						.disableDefaultConstraintViolation();
			}
			return valido;
		} else {
			return true;
		}
	}

	private boolean validarPadrao(String nomeUsuario, String padrao, String mensagem) {
		if (Pattern.compile(padrao).matcher(nomeUsuario).find()) {
			return true;
		} else {
			acrescentarNaMensagemFinal(mensagem);
			return false;
		}
	}

	private boolean validarCaractereEspecial(String nomeUsuario) {
		for (char caractere : nomeUsuario.toCharArray()) {
			if (LISTA_CARACTERES_ESPECIAIS.contains(String.valueOf(caractere))) {
				return true;
			}
		}
		acrescentarNaMensagemFinal("Ao menos um caractere especial");
		return false;
	}

	private boolean validarTamanho(String nomeUsuario, int minimo, int maximo, String mensagem) {
		if (nomeUsuario.length() >= minimo && nomeUsuario.length() <= maximo) {
			return true;
		} else {
			acrescentarNaMensagemFinal(mensagem);
			return false;
		}
	}

	private void acrescentarNaMensagemFinal(String mensagem) {
		if (!mensagemFinal.isEmpty()) {
			mensagemFinal += "; ";
		}
		mensagemFinal += mensagem;
	}
}