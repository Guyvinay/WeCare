package com.weCare.security;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTTokenService {

	@Value("${app.weCare.secret.key}")
	private String jwtSecretKey;
	
	@Value("${app.weCare.expiration.time}")
	private long expirationTime;
	
	public String generateJWTToken(Authentication authentication) {
		
		String username = authentication.getName();

        Date currentDate = new Date();

        Date expireDate = new Date(new Date().getTime()+ 30000000);
        
        String token = Jwts
  			  .builder()
  			  .setIssuer(SecurityConstants.ISSUER)
  			  .setSubject(SecurityConstants.SUBJECT)
  			  .claim("username", username)
  			  .claim("authority", populateAuthorities(authentication.getAuthorities()))
  			  .setIssuedAt(currentDate)
  			  .setExpiration(expireDate)//Set 5 Mins of expiration time
  			  .signWith(getSecretKey())
  			  .compact();
		
		return token;
	}
	
	public SecretKey getSecretKey() {
//		return Keys.hmacShaKeyFor(jwtSecretKey.getBytes());
		return Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes());
	}
	
	public String populateAuthorities(Collection<? extends GrantedAuthority> collection) {

    	Set<String> authoritiesSet = new HashSet<>();

        for (GrantedAuthority authority : collection) {
            authoritiesSet.add(authority.getAuthority());
        }
        return String.join(",", authoritiesSet);


    }
	
}
