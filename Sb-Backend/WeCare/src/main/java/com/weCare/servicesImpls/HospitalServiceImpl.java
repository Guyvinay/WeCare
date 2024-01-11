package com.weCare.servicesImpls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weCare.exceptions.HospitalNotFoundException;
import com.weCare.exceptions.NotFoundException;
import com.weCare.modals.Address;
import com.weCare.modals.Hospital;
import com.weCare.repository.AddressRepository;
import com.weCare.repository.HospitalRepository;
import com.weCare.services.HospitalService;

@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;
    
    @Autowired
    private AddressRepository addressRepository;
    
    @Override
    public Hospital saveHospital(Hospital hospital) {
    	
    	Address address = addressRepository.save(hospital.getAddress());
    	hospital.setAddress(address);
    	
        return hospitalRepository.save(hospital);
    }
    
    @Override
	public List<Hospital> saveHospitals(List<Hospital> hospitals) {
    	
    	if(hospitals.isEmpty())
    		throw new HospitalNotFoundException("Hospitals not found to persist!!!");
    	
    	for(Hospital hospital:hospitals) {
    		hospital.setAddress(
    				addressRepository.save(
    						hospital.getAddress()
    						)
    				);
    	}
    	
		return hospitalRepository.saveAll(hospitals);
	}
    
    @Override
    public Hospital getHospitalById(String hospital_id) {

        return hospitalRepository
                .findById(hospital_id).orElseThrow(()->
                        new NotFoundException("Hospital with id: "+hospital_id+", not found!!!")
                );
    }

    @Override
    public List<Hospital> getAllHospitals() {
        List<Hospital> hospitals = hospitalRepository.findAll();
        if(hospitals.isEmpty())throw new NotFoundException("No Hospitals Found!!!");
        return hospitals;
    }

    @Override
    public Hospital updateHospital(String hospital_id, Hospital hospital) {
        return null;
    }

    @Override
    public String deleteHospitalById(String hospital_id) {
        Hospital hospital = hospitalRepository
                .findById(hospital_id).orElseThrow(()->
                        new NotFoundException("Hospital with id: "+hospital_id+", not found!!!")
                );
        hospitalRepository.delete(hospital);
        return "Hospital with id: "+hospital_id+", deleted successfully";
    }

	
}
