package com.weCare.servicesImpls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weCare.modals.Profile;
import com.weCare.repository.ProfileRepository;
import com.weCare.services.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private ProfileRepository profileRepository;
	
	@Override
	public Profile saveProfile(Profile profile) {
		Profile savedProfile = profileRepository.save(profile);
		return savedProfile;
	}

	@Override
	public Profile getProfileById(String profile_id) {
		
		return null;
	}

	@Override
	public List<Profile> getAllProfiles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Profile updateProfile(String profile_id, Profile profile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteProfileById(String profile_id) {
		// TODO Auto-generated method stub
		return null;
	}

}
