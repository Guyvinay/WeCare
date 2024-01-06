package com.weCare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weCare.modals.Patient;

public interface PatientRepository extends JpaRepository<Patient, String> {

}
