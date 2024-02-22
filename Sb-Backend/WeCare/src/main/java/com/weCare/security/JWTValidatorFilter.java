package com.weCare.security;

import java.io.IOException;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTValidatorFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String jwtToken = request.getHeader(SecurityConstants.JWT_HEADER);
//		System.out.println(jwtToken);
		
		if(jwtToken!=null) {
			jwtToken = jwtToken.substring(7);
//			System.out.println(jwtToken);
			
			SecretKey secretKey = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes());
			
			Claims claims = Jwts
								.parserBuilder()
								.setSigningKey(secretKey)
								.build()
								.parseClaimsJws(jwtToken)
								.getBody();
//			System.out.println(claims);
			String username = String.valueOf(claims.get("username"));
			String authority = String.valueOf(claims.get("authority"));
			
			Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, AuthorityUtils.commaSeparatedStringToAuthorityList(authority));
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		filterChain.doFilter(request, response);
	}
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//		System.out.println(request.getServletPath());
		return request.getServletPath().equals("/doctors/basicLogin");
	}

}
