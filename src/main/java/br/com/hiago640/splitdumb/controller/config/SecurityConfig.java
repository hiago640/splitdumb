package br.com.hiago640.splitdumb.controller.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/css/**", "/js/**").permitAll()
				.anyRequest().authenticated()
                .and()
            .formLogin()
//                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.jdbcAuthentication().dataSource(dataSource)
	            .usersByUsernameQuery("select username, senha, ativo from pessoas where username = ?")
	            .authoritiesByUsernameQuery(
	                    "SELECT username, 'ROLE_USER' FROM pessoas WHERE username=?")
	            .passwordEncoder(passwordEncoder());
	}
//
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//			.withUser("admin").password("{noop}admin").roles("ADMIN");
//		// .withUser("outro").password("12345").roles("ADMIN", "USUARIO");
//	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		// A) Usar um PasswordEncoder fixo
		// int tamanhoSalt = 16; // em bytes (=128 bits)
		// int tamanhoHash = 32; // em bytes (=256 bits)
		// int paralelismo = 1; // atualmente não suportado pelo Spring Security
		// int memoria = 4096; // custo de memoria (em kibibytes 2x10 bytes = 1024
		// bytes)
		// int iteracoes = 3;
		// Argon2PasswordEncoder passwordEncoder = new
		// Argon2PasswordEncoder(tamanhoSalt,
		// tamanhoHash,
		// paralelismo,
		// memoria,
		// iteracoes);
		// return passwordEncoder;
		// B) Usar um PasswordEncoder que aceite todos os esquemas disponiveis no Spring
		// Security
		// ao mesmo tempo. As senhas comecam dizendo qual o esquema usado {noop},
		// {bcrypt}, {argon2}
		// return PasswordEncoderFactories.createDelegatingPasswordEncoder();
		// C) Usar um PasswordEncoder que aceite todos os esquemas disponiveis no Spring
		// Security
		// ao mesmo tempo mas definindo qual usar se nao existir uma identificacao
		// presente.
		// As senhas comecam dizendo qual o esquema usado {noop}, {bcrypt}, {argon2}
		// DelegatingPasswordEncoder delegatingPasswordEncoder =
		// (DelegatingPasswordEncoder) PasswordEncoderFactories
		// .createDelegatingPasswordEncoder();
		// delegatingPasswordEncoder.setDefaultPasswordEncoderForMatches(new
		// Argon2PasswordEncoder());
		// return delegatingPasswordEncoder;
		// D) Usar um PasswordEncoder que aceite todos os esquemas disponiveis no Spring
		// Security
		// mas escolhendo quais vamos aceitar. As senhas comecam dizendo qual o esquema
		// usado {noop}, {bcrypt}, {argon2}
		String idEnconder = "argon2";
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put("argon2", new Argon2PasswordEncoder());
		encoders.put("noop", NoOpPasswordEncoder.getInstance());
		// encoders.put("bcrypt", new BCryptPasswordEncoder());
		// encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
		// encoders.put("scrypt", new SCryptPasswordEncoder());
		// encoders.put("sha256", new StandardPasswordEncoder());
		PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(idEnconder, encoders);
		return passwordEncoder;
	}

}