package com.weCare.services;

import com.weCare.modals.Address;

import java.util.List;

public interface AddressService {

    public Address saveAddress(Address address);

    public Address getAddressById(String address_id);

    public List<Address> getAllAddresses();

    public Address updateAddress(String address_id, Address address);

    public String deleteAddressById(String address_id);
    
}
