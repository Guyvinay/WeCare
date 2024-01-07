package com.weCare.servicesImpls;

import com.weCare.exceptions.NotFoundException;
import com.weCare.modals.Doctor;
import com.weCare.modals.Hospital;
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

        return doctorRepository
                .findById(doctor_id).orElseThrow(()->
                        new NotFoundException("Doctor with id: "+doctor_id+", not found!!!")
                );
    }

    @Override
    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        if(doctors.isEmpty())throw new NotFoundException("No Doctors Found!!!");
        return doctors;
    }

    @Override
    public Doctor updateDoctor(String doctor_id, Doctor doctor) {
        return null;
    }

    @Override
    public String deleteDoctorById(String doctor_id) {
        Doctor doctor = doctorRepository
                .findById(doctor_id).orElseThrow(()->
                        new NotFoundException("Doctor with id: "+doctor_id+", not found!!!")
                );
        doctorRepository.delete(doctor);
        return "Doctor with id: "+doctor_id+", deleted successfully";
    }
}
