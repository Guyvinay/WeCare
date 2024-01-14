package com.weCare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weCare.modals.Message;

public interface MessageRepository extends JpaRepository<Message, String> {

    List<Message> findAllByOrderByTimeStampDesc();

    List<Message> findAllByOrderByTimeStampAsc();
}
