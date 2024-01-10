package com.weCare.servicesImpls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weCare.exceptions.NotFoundException;
import com.weCare.modals.Address;
import com.weCare.repository.AddressRepository;
import com.weCare.services.AddressService;

@Service
public class AddressServiceImpl implements AddressService {


    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address saveAddress(Address address) {

        return addressRepository.save(address);
    }

    @Override
    public Address getAddressById(String address_id) {

        return addressRepository
                .findById(address_id).orElseThrow(()->
                        new NotFoundException("Address with id: "+address_id+", not found!!!")
                );
    }

    @Override
    public List<Address> getAllAddresses() {

        List<Address> addresses = addressRepository.findAll();
        if(addresses.isEmpty())throw new NotFoundException("No Addresses Found!!!");
        return addresses;
    }

    @Override
    public Address updateAddress(String address_id, Address address) {
        return null;
    }

    @Override
    public String deleteAddressById(String address_id) {

        Address address = addressRepository
                .findById(address_id).orElseThrow(()->
                        new NotFoundException("Address with id: "+address_id+", not found!!!")
                );
        addressRepository.delete(address);
        return "Address with id: "+address_id+", deleted successfully";
    }
}
