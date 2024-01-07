package com.weCare.repository;

import com.weCare.modals.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import com.weCare.modals.Doctor;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, String> {

    public List<Doctor> findByDepartmentAndHospital(Department department, String hospital_id);

}
