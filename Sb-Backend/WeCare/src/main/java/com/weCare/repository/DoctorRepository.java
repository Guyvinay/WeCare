package com.weCare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weCare.modals.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, String> {

}
