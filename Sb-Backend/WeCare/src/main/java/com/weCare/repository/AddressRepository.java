package com.weCare.repository;

import com.weCare.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.weCare.modals.Address;

public interface AddressRepository extends JpaRepository<Address, String> {


    
}
