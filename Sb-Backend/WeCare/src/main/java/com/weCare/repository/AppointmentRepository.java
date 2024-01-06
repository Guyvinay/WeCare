package com.weCare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weCare.modals.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, String> {

}
