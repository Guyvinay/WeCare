package com.weCare.repository;

import com.weCare.modals.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import com.weCare.modals.Doctor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, String> {

    @Query("SELECT d from Doctor d join d.hospital h where h.hospital_id=:hospital_id AND d.department=:department")
    public List<Doctor> findByDepartmentAndHospital(Department department, String hospital_id);

}
