package com.weCare.services;

import java.util.List;

import com.weCare.modals.Doctor;

public interface DoctorService {

	public Doctor saveDoctor(Doctor doctor);

	public List<Doctor> saveDoctors(List<Doctor> doctors, String hospital_id);

	public Doctor saveDoctorWithHospital(Doctor doctor, String hospital_id);

	public Doctor updateDoctorHospital(String doctor_id, String hospital_id);

	public Doctor getDoctorById(String doctor_id);

	public List<Doctor> getDoctorByDepartmentPattern(String doctor_department);

	public List<Doctor> getDoctorByNamePattern(String doctor_name);

	public List<Doctor> getAllDoctors();

	public Doctor updateDoctor(String doctor_id, Doctor doctor);

	public String deleteDoctorById(String doctor_id);

}
