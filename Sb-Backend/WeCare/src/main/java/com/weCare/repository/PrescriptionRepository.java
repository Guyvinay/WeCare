package com.weCare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weCare.modals.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, String> {

}
