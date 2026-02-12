package com.weCare.repository;

/*
import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.weCare.modals.Slot;
import com.weCare.modals.SlotPeriod;
public interface SlotRepository extends JpaRepository<Slot, String> {

	@Query("SELECT COUNT(s) FROM Slot s " +
	           "WHERE s.slotDate = :slotDate " +
	           "AND s.slotPeriod = :slotPeriod " +
	           "AND s.slotStatus = 'BOOKED' " )
	@Query("SELECT COUNT(s) FROM Slot s " +
           "WHERE s.slotDate = :slotDate " +
           "AND s.slotPeriod = :slotPeriod " +
           "AND s.doctor.doctorId = :doctorId")
	    int countSlotsForDoctor(@Param("slotDate") LocalDate slotDate,
	                            @Param("slotPeriod") SlotPeriod slotPeriod);
	
}

*/
