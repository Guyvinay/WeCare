package com.weCare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weCare.modals.Address;

public interface AddressRepository extends JpaRepository<Address, String> {

}
