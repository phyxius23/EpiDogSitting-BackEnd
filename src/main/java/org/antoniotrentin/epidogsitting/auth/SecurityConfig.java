package org.antoniotrentin.epidogsitting.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
	JWTAuthFilter jwtAuthFilter;

	@Autowired
	CorsFilter corsFilter;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		//http.cors(c -> c.disable()); //=> http.cors(AbstractHttpConfigurer::disable);
		http.csrf(c -> c.disable()); //=> http.csrf(AbstractHttpConfigurer::disable);

		http.authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**").permitAll());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/swagger-ui/**").permitAll());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/api-docs/**").permitAll());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/addresses/**").authenticated());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/bookings/**").authenticated());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/dogs/**").authenticated());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/dogowners/**").authenticated());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/dogsitters/**").authenticated());
		//		http.authorizeHttpRequests(auth -> auth.requestMatchers("/dogsitters/**").permitAll());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/favorites/**").authenticated());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/offerings/**").authenticated());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/reviews/**").authenticated());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/api/**").authenticated());

		//http.authorizeHttpRequests(auth -> auth.requestMatchers("/users/**").hasRole(null));

		/**
		 * MODIFICHE DA TESTARE
		 */
		//		http.authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**").permitAll());
		//
		//		http.authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.GET, "/addresses/**").authenticated());
		//		http.authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.GET, "/bookings/**").authenticated());
		//		http.authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.GET, "/dogs/**").authenticated());
		//		http.authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.GET, "/dogowners/**").authenticated());
		//		http.authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.GET, "/dogsitters/**").authenticated());
		//		http.authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.GET, "/favorites/**").authenticated());
		//		http.authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.GET, "/offerings/**").authenticated());
		//		http.authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.GET, "/reviews/**").authenticated());

		// http.addFilterBefore(exceptionHandlerFilter, JWTAuthFilter.class);
		http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(corsFilter, JWTAuthFilter.class);

		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		return http.build();
	}

	@Bean
	PasswordEncoder pwEncoder() {
		return new BCryptPasswordEncoder(10);
	}
}
