package com.weCare.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weCare.modals.Address;
import com.weCare.services.AddressService;

@RestController
@RequestMapping(value = "/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping()
    public ResponseEntity<Address> saveAddress(@RequestBody Address address){
        return new ResponseEntity<Address>(
                addressService.saveAddress(address),
                HttpStatus.ACCEPTED
        );
    }
    
    @GetMapping(value = "/{address_id}")
    public ResponseEntity<Address> getAddressById(
    		                           @PathVariable("address_id") String address_id){
        return new ResponseEntity<Address>(
                addressService.getAddressById(address_id),
                HttpStatus.ACCEPTED
        );
    }
    
    @GetMapping()
    public ResponseEntity<List<Address>> getAllAddresses(){
        return new ResponseEntity<List<Address>>(
                addressService.getAllAddresses(),
                HttpStatus.ACCEPTED
        );
    }
    
    @PutMapping("/{address_id}")
    public ResponseEntity<Address> updateAddress( 
    		                         @PathVariable("address_id") String address_id,
                                     @RequestBody Address address){
        return new ResponseEntity<Address>(
                addressService.updateAddress(address_id, address),
                HttpStatus.ACCEPTED
        );
    }
    
    @DeleteMapping(value = "/{address_id}")
    public ResponseEntity<String> deleteAddressById(
    		                        @PathVariable("address_id") String address_id){
        return new ResponseEntity<String>(
                addressService.deleteAddressById(address_id),
                HttpStatus.ACCEPTED
        );
    }

}
