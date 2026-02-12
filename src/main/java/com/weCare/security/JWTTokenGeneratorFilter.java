package com.weCare.security;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTTokenGeneratorFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
//		System.out.println("From Generator");
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		System.out.println(authentication);
		
		if(authentication!=null) {
			
			SecretKey secretKey = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes());
			
			String tokenString = Jwts
			  .builder()
			  .setIssuer(SecurityConstants.ISSUER)
			  .setSubject(SecurityConstants.SUBJECT)
			  .claim("username", authentication.getName())
			  .claim("authority", populateAuthorities(authentication.getAuthorities()))
			  .setIssuedAt(new Date())
			  .setExpiration(new Date(new Date().getTime()+ 300000))//Set 5 Mins of expiration time
			  .signWith(secretKey)
			  .compact();
			
//			System.out.println("From Generator");
			
			response.setHeader(SecurityConstants.JWT_HEADER, tokenString);
		}
		
		filterChain.doFilter(request, response);
		
	}
	
	private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {

    	Set<String> authoritiesSet = new HashSet<>();

        for (GrantedAuthority authority : collection) {
            authoritiesSet.add(authority.getAuthority());
        }
        return String.join(",", authoritiesSet);


    }
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//		System.out.println(request.getServletPath());
//		return !request.getServletPath().equals("/doctors/basicLogin")||
//		      return !request.getServletPath().equals("/doctors/customLogin");
		boolean shouldNotFilter = request.getServletPath().equals("/login/basic") || request.getServletPath().equals("/login/custom");
//		System.out.println(shouldFilter);
 		return !shouldNotFilter;
	}

}
