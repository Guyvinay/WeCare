package com.weCare.servicesImpls;

import com.weCare.modals.Doctor;
import com.weCare.repository.DoctorRepository;
import com.weCare.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public Doctor saveDoctor(Doctor doctor) {


        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor getDoctorById(String doctor_id) {

        return doctorRepository.findById(doctor_id).get();
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor updateDoctor(String doctor_id, Doctor doctor) {
        return null;
    }

    @Override
    public String deleteDoctorById(String doctor_id) {
        return null;
    }
}
