package com.weCare.services;

import java.util.List;

import com.weCare.modals.Profile;

public interface ProfileService {

	public Profile saveProfile(Profile profile);
	public Profile getProfileById(String profile_id);
	public List<Profile> getAllProfiles();
	public Profile updateProfile(String profile_id, Profile profile);
	public String deleteProfileById(String profile_id);
	
}
