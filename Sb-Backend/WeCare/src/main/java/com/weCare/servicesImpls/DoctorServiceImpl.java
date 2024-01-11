package com.weCare.servicesImpls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weCare.exceptions.DoctorNotFoundException;
import com.weCare.exceptions.HospitalNotFoundException;
import com.weCare.exceptions.NotFoundException;
import com.weCare.modals.Address;
import com.weCare.modals.Department;
import com.weCare.modals.Doctor;
import com.weCare.modals.Hospital;
import com.weCare.repository.AddressRepository;
import com.weCare.repository.DoctorRepository;
import com.weCare.repository.HospitalRepository;
import com.weCare.services.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private AddressRepository addressRepository;
    
    @Override
    public Doctor saveDoctor(Doctor doctor) {
    	
    	Address address = addressRepository.save(doctor.getAddress());
    	doctor.setAddress(address);
        return doctorRepository.save(doctor);
    }
    
    @Override
	public List<Doctor> saveDoctors(List<Doctor> doctors) {
    	
    	if(doctors.isEmpty())
    		throw new DoctorNotFoundException("Doctors not found to persist!!!");
		
    	for(Doctor doctor:doctors) {
    		doctor.setAddress(
    				addressRepository.save(
    						doctor.getAddress()
    						)
    				);
    	}
    	
    	return doctorRepository.saveAll(doctors);
	}
    
    @Override
	public Doctor saveDoctorWithHospital(Doctor doctor, String hospital_id) {
    	
    	Hospital hospital = hospitalRepository
                .findById(hospital_id).orElseThrow(()->
                        new HospitalNotFoundException("Hospital with id: "+hospital_id+", not found!!!")
                );
    	Address address = addressRepository.save(doctor.getAddress());
    	
    	doctor.setAddress(address);
    	doctor.setHospital(hospital);
    	
    	hospital.getDoctors().add(doctor);
    	
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
    public List<Doctor> getDoctorByDepartmentPattern(String doctor_department) {
    	Department department = Department.valueOf(doctor_department);
        List<Doctor> doctorList = doctorRepository.findByDepartmentPattern(department);
        if(doctorList.isEmpty())throw new DoctorNotFoundException("Doctors not found for "+doctor_department+" department!!!");
        return doctorList;
    }

    @Override
    public List<Doctor> getDoctorByNamePattern(String doctor_name) {
    	List<Doctor> doctorList = doctorRepository.findByNamePattern(doctor_name);
        if(doctorList.isEmpty())throw new DoctorNotFoundException("Doctors '"+doctor_name+"', not found!!!");
        return doctorList;
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
    public Doctor updateDoctorHospital(String doctor_id, String hospital_id) {
        Hospital hospital = hospitalRepository
                .findById(hospital_id).orElseThrow(()->
                        new HospitalNotFoundException("Hospital with id: "+hospital_id+", not found!!!")
                );
        Doctor doctor = doctorRepository
                .findById(doctor_id).orElseThrow(()->
                        new DoctorNotFoundException("Doctor with id: "+doctor_id+", not found!!!")
                );
        doctor.setHospital(hospital);
        return doctorRepository.save(doctor);
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
