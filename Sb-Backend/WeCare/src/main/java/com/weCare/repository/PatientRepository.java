package com.weCare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.weCare.modals.Patient;

public interface PatientRepository extends JpaRepository<Patient, String> {

	@Query("SELECT p FROM Patient p WHERE p.name LIKE :name")
	public List<Patient> findByNamePattern(String name);

}
