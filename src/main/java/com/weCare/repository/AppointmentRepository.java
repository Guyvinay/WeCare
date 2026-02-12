package com.weCare.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.weCare.modals.Appointment;
import com.weCare.modals.SlotPeriod;


public interface AppointmentRepository extends JpaRepository<Appointment, String> {

	@Query(
			"SELECT a FROM Appointment a " +
	        "WHERE a.doctor.profile_id = :doctorId " +
	        		"AND "+
			" a.slot = :slot "+
					"AND "+
	        " a.appointment_date = :date"
			)
	List<Appointment> findAppointmentByDateAndSlot(@Param("doctorId") String doctorId, @Param("slot") SlotPeriod slot, @Param("date") LocalDate date);
	
}
