package com.weCare.services;

import com.weCare.modals.LoginDto;
import com.weCare.modals.LoginResponse;

public interface LoginService {

	public String plainLoginHaHa();
	
	public LoginResponse customLogin(LoginDto loginDto);
	
}
