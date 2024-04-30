package br.com.hiago640.splitdumb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Value("${spring.mail.username}")
	private String remetente;

	public String sendEmail(String destinatario, String assunto, String menssagem) {

		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setFrom(remetente);
			mailMessage.setTo(destinatario);
			mailMessage.setSubject(assunto);
			mailMessage.setText(menssagem);

			mailSender.send(mailMessage);
			return "Email enviado";
		} catch (Exception e) {
			return "Erro ao enviar email" + e.getLocalizedMessage();
		}

	}

}
