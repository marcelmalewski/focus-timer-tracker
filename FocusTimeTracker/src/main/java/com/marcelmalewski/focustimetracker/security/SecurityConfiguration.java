package com.marcelmalewski.focustimetracker.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//TODO dodać profile
//TODO co to dokladnie stateless session
//TODO jaki powinien byc dostep do dokumentacji
//TODO obargnac sesjie co to znaczy
//			.sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {
	//TODO dodac specjalna permisje dostepu do swaggera

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf(AbstractHttpConfigurer::disable)
			.authorizeHttpRequests(authorize -> authorize
				//Documentation
				.requestMatchers(
					HttpMethod.GET,
					"/docs",
					"/v2/api-docs/**",
					"/v3/api-docs/**",
					"/swagger-resources/**",
					"/swagger-ui/**",
					"/swagger-ui.html"
				)
				.permitAll()

				//Views post
				.requestMatchers(
					HttpMethod.POST,
					"/register/validation"
				)
				.permitAll()

				//Views get
				.requestMatchers(
					HttpMethod.GET,
					"/output.css",
					"/register",
					"/welcome"
				)
				.permitAll()

				.requestMatchers(
					"/error"
				)
				.permitAll()

				.anyRequest()
				.authenticated()
			)

			.formLogin(formLogin -> formLogin
				.loginPage("/login")
				.loginProcessingUrl("/login-perform")
				.usernameParameter("loginOrEmail")
				.defaultSuccessUrl("/timer")
				.permitAll()
			)

			.logout(logout -> logout
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login")
				.permitAll()
			);

		return http.build();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
