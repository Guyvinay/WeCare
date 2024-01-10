package com.weCare.services;

import java.util.List;

import com.weCare.modals.Hospital;

public interface HospitalService {

    public Hospital saveHospital(Hospital hospital);

    public Hospital getHospitalById(String hospital_id);

    public List<Hospital> getAllHospitals();

    public Hospital updateHospital(String hospital_id, Hospital hospital);

    public String deleteHospitalById(String hospital_id);
    
}
