package com.weCare.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.weCare.modals.Medication;

public interface MedicationRepository extends JpaRepository<Medication, String> {

	@Query("SELECT m FROM Medication m WHERE m.medication_id IN :ids")
	public List<Medication> findByMedication_idIn(List<String> ids);

	//After renaming primary key of Medication from medication_id to id, this would work;
//	public List<Medication> findByMedication_idIn(List<String> ids);
	
}
