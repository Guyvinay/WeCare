package com.weCare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weCare.modals.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, String> {

}
