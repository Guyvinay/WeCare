package com.weCare.servicesImpls;

import com.weCare.exceptions.NotFoundException;
import com.weCare.modals.Hospital;
import com.weCare.modals.Patient;
import com.weCare.repository.HospitalRepository;
import com.weCare.services.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;


    @Override
    public Hospital saveHospital(Hospital hospital) {

        return hospitalRepository.save(hospital);
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
