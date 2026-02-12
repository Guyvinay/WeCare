package com.weCare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weCare.modals.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MessageRepository extends JpaRepository<Message, String> {

    List<Message> findAllByOrderByTimeStampDesc();

    List<Message> findAllByOrderByTimeStampAsc();

    @Query("SELECT m FROM Message m WHERE m.appointment.appointment_id=:appointment_id")
    List<Message> findMessagesByAppointmentId(@Param("appointment_id") String appointment_id);
}
