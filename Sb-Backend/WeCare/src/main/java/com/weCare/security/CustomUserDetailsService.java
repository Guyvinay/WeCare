package com.weCare.security;

import java.util.ArrayList;
import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.weCare.exceptions.NotFoundException;
//import com.weCare.modals.Profile;
//import com.weCare.repository.ProfileRepository;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//	@Autowired
//	private ProfileRepository profileRepository;
//	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Profile profile = profileRepository.findByEmail(username)
//		.orElseThrow(() -> new NotFoundException("Profile with email: " + username + ", not found!!!"));
//		
//		List<GrantedAuthority> grantedAuthority = new ArrayList<>();
//		System.out.println(profile);
//		grantedAuthority.add(new SimpleGrantedAuthority(profile.getRole().toString()));
//		
//		return new User(username, username, grantedAuthority);
//	}
//
//}
