package br.com.hiago640.splitdumb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@SpringBootApplication
public class Application {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		logger.info(">>>> Iniciando a execucao da aplicacao");
		SpringApplication.run(Application.class, args);
		logger.info(">>>> Aplicacao inicializada");
	}

	@Bean
	public LayoutDialect layoutDialect() {
	    return new LayoutDialect();
	}
}