package com.weCare.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weCare.modals.Profile;
import java.util.List;


public interface ProfileRepository extends JpaRepository<Profile, String> {

	public Optional<Profile> findByEmail(String email);
	
}
