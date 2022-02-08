package com.generation.blogpessoal.BlogPessoal.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuração do spring security
 * 
 * @author Jessica Curti
 * @date 08/02/2022
 * @version 1.0
 * @see WebSecurityConfigurerAdapter
 * @see EnableWebSecurity
 * @see PasswordEncoder
 * @see BCryptPasswordEncoder
 * @see SessionCreationPolicy
 * @see HttpSecurity
 * @see AuthenticationManagerBuilder
 * @see HttpMethod
 * @see ResponseStatusException
 * @see HttpStatus
 * @see UserServices
 *
 */

@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {

	private @Autowired UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService);
		
		auth.inMemoryAuthentication().withUser("jess-curti").password(passwordEncoder().encode("jess-curti"))
			.authorities("ROLE_ADMIN");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
					.antMatchers("/usuario/logar").permitAll()
					.antMatchers("/usuario/cadastrar").permitAll()
					.anyRequest().authenticated()
				.and().httpBasic()
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().cors()
				.and().csrf().disable();
	}
	
	
}
