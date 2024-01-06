package com.weCare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weCare.modals.Profile;

public interface ProfileRepository extends JpaRepository<Profile, String> {

}
