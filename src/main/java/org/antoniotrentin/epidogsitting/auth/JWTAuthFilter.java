package org.antoniotrentin.epidogsitting.auth;

import java.io.IOException;

import org.antoniotrentin.epidogsitting.entities.User;
import org.antoniotrentin.epidogsitting.exceptions.NotFoundException;
import org.antoniotrentin.epidogsitting.exceptions.UnauthorizedException;
import org.antoniotrentin.epidogsitting.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {
	@Autowired
	UserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authHeader = request.getHeader("Authorization");

		if (authHeader == null || !authHeader.startsWith("Bearer "))
			throw new UnauthorizedException("Per favore aggiungi il token all'authorization header");

		String accessToken = authHeader.substring(7);
		JWTTools.isTokenValid(accessToken);

		String email = JWTTools.extractSubject(accessToken);
		try {
			User user = userService.findByEmail(email);

			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null,
					user.getAuthorities());
			authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authToken);
			filterChain.doFilter(request, response);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		return new AntPathMatcher().match("/auth/**", request.getServletPath())
				|| new AntPathMatcher().match("/swagger-ui/**", request.getServletPath())
				|| new AntPathMatcher().match("/api-docs/**", request.getServletPath());
	}
	//  da configurare
	//	@Override
	//	protected boolean shouldNotFilter(HttpServletRequest request) {
	//		String[] patterns = { "/auth/**", "/swagger-ui/**", "/api-docs/**" };
	//		AntPathMatcher matcher = new AntPathMatcher();
	//		boolean match = false;
	//		for (String pattern : patterns) {
	//			if (matcher.match(pattern, request.getServletPath())) {
	//				match = true;
	//				break;
	//			}
	//		}
	//
	//		return match;
	//	}

}
