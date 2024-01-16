package com.weCare.servicesImpls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weCare.exceptions.DoctorNotFoundException;
import com.weCare.exceptions.HospitalNotFoundException;
import com.weCare.exceptions.NotFoundException;
import com.weCare.modals.Address;
import com.weCare.modals.Availability;
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
	public List<Doctor> saveDoctors(List<Doctor> doctors, String hospital_id) {

		if (doctors.isEmpty())
			throw new DoctorNotFoundException("Doctors not found to persist!!!");

		Hospital hospital = hospitalRepository.findById(hospital_id).orElseThrow(
				() -> new HospitalNotFoundException("Hospital with id: " + hospital_id + ", not found!!!"));

		for (Doctor doctor : doctors) {
			doctor.setAddress(addressRepository.save(doctor.getAddress()));
			doctor.setHospital(hospital);
			doctor.setAvailability(Availability.AVAILABLE);

			hospital.getDoctors().add(doctor);
		}

		return doctorRepository.saveAll(doctors);
	}

	@Override
	public Doctor saveDoctorWithHospital(Doctor doctor, String hospital_id) {

		Hospital hospital = hospitalRepository.findById(hospital_id).orElseThrow(
				() -> new HospitalNotFoundException("Hospital with id: " + hospital_id + ", not found!!!"));
		Address address = addressRepository.save(doctor.getAddress());

		doctor.setAvailability(Availability.AVAILABLE);
		doctor.setAddress(address);
		doctor.setHospital(hospital);

		hospital.getDoctors().add(doctor);

		return doctorRepository.save(doctor);
	}

	@Override
	public Doctor getDoctorById(String doctor_id) {

		return doctorRepository.findById(doctor_id)
				.orElseThrow(() -> new NotFoundException("Doctor with id: " + doctor_id + ", not found!!!"));
	}

	@Override
	public List<Doctor> getDoctorByDepartmentPattern(String doctor_department) {

		List<Doctor> doctorList = doctorRepository.findByDepartmentContaining(doctor_department);
		if (doctorList.isEmpty())
			throw new DoctorNotFoundException("Doctors not found for " + doctor_department + " department!!!");
		return doctorList;
	}

	@Override
	public List<Doctor> getDoctorByNamePattern(String doctor_name) {
		List<Doctor> doctorList = doctorRepository.findByNamePattern(doctor_name);
		if (doctorList.isEmpty())
			throw new DoctorNotFoundException("Doctors '" + doctor_name + "', not found!!!");
		return doctorList;
	}

	@Override
	public List<Doctor> getAllDoctors() {
		List<Doctor> doctors = doctorRepository.findAll();
		if (doctors.isEmpty())
			throw new NotFoundException("No Doctors Found!!!");
		return doctors;
	}

	@Override
	public Doctor updateDoctor(String doctor_id, Doctor doctor) {

		Doctor retrieved_doctor = doctorRepository.findById(doctor_id)
				.orElseThrow(() -> new NotFoundException("Doctor with id: " + doctor_id + ", not found!!!"));
		if (doctor.getAvailability() != null) {
			retrieved_doctor.setAvailability(doctor.getAvailability());
		}
		if (doctor.getDoctor_name() != null && doctor.getDoctor_name().length() > 0) {
			retrieved_doctor.setDoctor_name(doctor.getDoctor_name());
		}
		if (doctor.getDepartment() != null) {
			retrieved_doctor.setDepartment(doctor.getDepartment());
		}

		return doctorRepository.save(retrieved_doctor);
	}

	@Override
	public Doctor updateDoctorHospital(String doctor_id, String hospital_id) {
		Hospital hospital = hospitalRepository.findById(hospital_id).orElseThrow(
				() -> new HospitalNotFoundException("Hospital with id: " + hospital_id + ", not found!!!"));
		Doctor doctor = doctorRepository.findById(doctor_id)
				.orElseThrow(() -> new DoctorNotFoundException("Doctor with id: " + doctor_id + ", not found!!!"));
		doctor.setHospital(hospital);
		hospital.getDoctors().add(doctor);
		return doctorRepository.save(doctor);
	}

	@Override
	public String deleteDoctorById(String doctor_id) {
		Doctor doctor = doctorRepository.findById(doctor_id)
				.orElseThrow(() -> new NotFoundException("Doctor with id: " + doctor_id + ", not found!!!"));
		doctorRepository.delete(doctor);
		return "Doctor with id: " + doctor_id + ", deleted successfully";
	}

}
