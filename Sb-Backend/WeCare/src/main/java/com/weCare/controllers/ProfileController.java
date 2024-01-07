package com.weCare.controllers;

import com.weCare.modals.Profile;
import com.weCare.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping()
    public ResponseEntity<Profile> saveProfile(@RequestBody Profile profile){
        return new ResponseEntity<Profile>(
                profileService.saveProfile(profile),
                HttpStatus.ACCEPTED
        );
    }
    @GetMapping(value = "/{profile_id}")
    public ResponseEntity<Profile> getProfileById(@PathVariable("profile_id") String profile_id){
        return new ResponseEntity<Profile>(
                profileService.getProfileById(profile_id),
                HttpStatus.ACCEPTED
        );
    }
    @GetMapping()
    public ResponseEntity<List<Profile>> getAllProfiles(){
        return new ResponseEntity<List<Profile>>(
                profileService.getAllProfiles(),
                HttpStatus.ACCEPTED
        );
    }
    @PutMapping("/{profile_id}")
    public ResponseEntity<Profile> updateProfile( @PathVariable("profile_id") String profile_id,
                                                  @RequestBody Profile profile){
        return new ResponseEntity<Profile>(
                profileService.updateProfile(profile_id, profile),
                HttpStatus.ACCEPTED
        );
    }
    @DeleteMapping(value = "/{profile_id}")
    public ResponseEntity<String> deleteProfileById(@PathVariable("profile_id") String profile_id){
        return new ResponseEntity<String>(
                profileService.deleteProfileById(profile_id),
                HttpStatus.ACCEPTED
        );
    }

}
