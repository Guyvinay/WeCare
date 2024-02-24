package com.weCare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weCare.modals.LoginDto;
import com.weCare.modals.LoginResponse;
import com.weCare.services.LoginService;
import com.weCare.services.ProfileService;

@RestController
@RequestMapping(value="/login")
public class LoginController {

	
	
	@Autowired
	private LoginService loginService;
	
	@PostMapping(value = "/basic")
	public ResponseEntity<String> loginWithBasicAuthentication(Authentication authentication){
		
		String name = authentication.getName();
		
		return new ResponseEntity<String>(name+" Logged-In", HttpStatus.ACCEPTED);
	}
	@PostMapping(value = "/custom")
	public ResponseEntity<LoginResponse> customLoginWithAuthenticationManager(@RequestBody LoginDto loginDto){
		
		
		
		LoginResponse customLogin = loginService.customLogin(loginDto);
		
		return new ResponseEntity<LoginResponse>(customLogin, HttpStatus.ACCEPTED);
	}
}
