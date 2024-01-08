package com.weCare.services;

import com.weCare.modals.Doctor;

import java.util.List;

public interface DoctorService {

    public Doctor saveDoctor(Doctor doctor);

    public Doctor getDoctorById(String doctor_id);

    public List<Doctor> getAllDoctors();

    public Doctor updateDoctor(String doctor_id, Doctor doctor);

    public Doctor updateDoctorHospital(String doctor_id, String hospital_id);

    public String deleteDoctorById(String doctor_id);
    
}
