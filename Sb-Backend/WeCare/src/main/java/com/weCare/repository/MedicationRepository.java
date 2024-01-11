package com.weCare.repository;

import com.weCare.modals.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication, String> {

}
