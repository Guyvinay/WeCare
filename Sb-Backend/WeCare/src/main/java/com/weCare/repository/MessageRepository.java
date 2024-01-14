package com.weCare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weCare.modals.Message;

public interface MessageRepository extends JpaRepository<Message, String> {

}
