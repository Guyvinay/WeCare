package com.weCare.servicesImpls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.weCare.modals.LoginDto;
import com.weCare.modals.LoginResponse;
import com.weCare.security.JWTTokenService;
import com.weCare.services.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
    private AuthenticationManager authenticationManager;
	
	
	@Override
	public String plainLoginHaHa() {
		
		return null;
	}

	public JWTTokenService jwtTokenService;
	public LoginResponse loginResponse;
	
	@Override
	public LoginResponse customLogin(LoginDto loginDto) {
		
		jwtTokenService = new JWTTokenService();
		loginResponse  = new LoginResponse();
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
				);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String name = authentication.getName();
//		System.out.println(name);
		
		String jwtToken = jwtTokenService.generateJWTToken(authentication);
		
		loginResponse.setToken(jwtToken);
		loginResponse.setUsername(name);
		
		return loginResponse;
	}

}
