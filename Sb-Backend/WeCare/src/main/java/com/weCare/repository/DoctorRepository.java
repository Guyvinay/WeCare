package com.weCare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.weCare.modals.Department;
import com.weCare.modals.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, String> {

	//Finding Doctors with department and hospitals_id;
    @Query("SELECT d FROM Doctor d JOIN d.hospital h WHERE h.hospital_id=:hospital_id AND d.department=:department")
    public List<Doctor> findByDepartmentAndHospital(Department department, String hospital_id);

    //SQL specific native query
//    @Query(value = "SELECT * FROM Doctors d WHERE UPPER(d.department) LIKE UPPER(CONCAT('%', :department, '%'))", nativeQuery = true)
    
//    @Query(value = "SELECT * FROM Doctors d WHERE UPPER(d.department) LIKE UPPER(CONCAT('%', :department, '%'))", nativeQuery = true)
    @Query("SELECT d FROM Doctor d WHERE d.department = :department")
    public List<Doctor> findByDepartmentPattern(@Param("department") Department department);

    @Query("SELECT d FROM Doctor d  WHERE d.doctor_name LIKE %:doctor_name%")
    public List<Doctor> findByNamePattern(@Param("doctor_name")String doctor_name);

//    @Query("SELECT d from Doctor d where d.department=:department")
//    public List<Doctor> findByDepartmentAndHospital(Department department);
}
