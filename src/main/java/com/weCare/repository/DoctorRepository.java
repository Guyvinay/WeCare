package com.weCare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.weCare.modals.Availability;
import com.weCare.modals.Department;
import com.weCare.modals.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, String> {

	// Finding Doctors with department and hospitals_id;
	@Query("SELECT d FROM Doctor d JOIN d.hospital h WHERE h.hospital_id=:hospital_id AND d.department=:department AND d.availability=:availability")
	public List<Doctor> findByDepartmentAvailabilityAndHospital(Department department, Availability availability,
			String hospital_id);

	@Query("SELECT d FROM Doctor d WHERE UPPER(d.department) LIKE UPPER(CONCAT('%', :department, '%'))")
	public List<Doctor> findByDepartmentContaining(String department);

	@Query("SELECT d FROM Doctor d  WHERE LOWER(d.name) LIKE LOWER(CONCAT('%',:name,'%'))")
	public List<Doctor> findByNamePattern(@Param("name") String name);
	
	@Query("SELECT d FROM Doctor d WHERE d.hospital.hospital_id=:hospital_id")
	public List<Doctor> findDoctorByHospitalId(@Param("hospital_id")String hospital_id);
}
