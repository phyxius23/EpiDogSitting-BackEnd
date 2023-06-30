package org.antoniotrentin.epidogsitting.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
//@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
	JWTAuthFilter jwtAuthFilter;

	//	@Autowired
	//	ExceptionHandlerFilter exceptionHandlerFilter;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.cors(c -> c.disable()); //=> http.cors(AbstractHttpConfigurer::disable);
		http.csrf(c -> c.disable()); //=> http.csrf(AbstractHttpConfigurer::disable);

		http.authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**").permitAll());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/addresses/**").authenticated());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/bookings/**").authenticated());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/dogs/**").authenticated());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/dogowners/**").authenticated());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/dogsitters/**").authenticated());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/services/**").authenticated());
		// http.authorizeHttpRequests(auth -> auth.requestMatchers("/users/**").hasRole(null));

		// http.addFilterBefore(exceptionHandlerFilter, JWTAuthFilter.class);
		http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		return http.build();
	}

	@Bean
	PasswordEncoder pwEncoder() {
		return new BCryptPasswordEncoder(10);
	}
}
