package br.com.hiago640.splitdumb.controller.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
// Qualquer um pode fazer requisições para essas URLs
				.antMatchers("/css/**", "/js/**", "/login").permitAll()
// Um usuário autenticado e com o papel ADMIN pode fazer requisições para essas URLs
				.antMatchers("/**").hasRole("ADMIN")
//.antMatchers("URL").hasAnyRole("ADMIN", "USUARIO")
				.and()
// A autenticação usando formulário está habilitada
				.formLogin()
// Uma página de login customizada
				.loginPage("/login");
// Define a URL para o caso de falha no login
//.failureUrl("/login-error");
	}
}