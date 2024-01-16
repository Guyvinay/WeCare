package com.weCare.servicesImpls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weCare.exceptions.NotFoundException;
import com.weCare.modals.Profile;
import com.weCare.repository.ProfileRepository;
import com.weCare.services.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private ProfileRepository profileRepository;

	@Override
	public Profile saveProfile(Profile profile) {

		return profileRepository.save(profile);
	}

	@Override
	public Profile getProfileById(String profile_id) {

		return profileRepository.findById(profile_id)
				.orElseThrow(() -> new NotFoundException("Profile with id: " + profile_id + ", not found!!!"));
	}

	@Override
	public List<Profile> getAllProfiles() {

		List<Profile> profiles = profileRepository.findAll();
		if (profiles.isEmpty())
			throw new NotFoundException("No Profiles Found!!!");
		return profiles;
	}

	@Override
	public Profile updateProfile(String profile_id, Profile profile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteProfileById(String profile_id) {
		Profile profile = profileRepository.findById(profile_id)
				.orElseThrow(() -> new NotFoundException("Profile with id: " + profile_id + ", not found!!!"));
		profileRepository.delete(profile);
		return "Profile with id: " + profile_id + ", deleted successfully";
	}

}
