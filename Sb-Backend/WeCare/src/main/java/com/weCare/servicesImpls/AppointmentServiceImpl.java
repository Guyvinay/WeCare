package com.weCare.servicesImpls;

import com.weCare.exceptions.NotFoundException;
import com.weCare.modals.*;
import com.weCare.repository.DoctorRepository;
import com.weCare.repository.HospitalRepository;
import com.weCare.repository.PatientRepository;
import com.weCare.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;

public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    PatientRepository patientRepository;

    @Override
    public Appointment bookAppointment(
                            Appointment appointment,
                            String hospital_id, String patient_id
                         ) {
        Hospital hospital = hospitalRepository
                             .findById(hospital_id).orElseThrow(()->
                                new NotFoundException("Hospital with id: "+hospital_id+", not found!!!")
                              );
        Patient patient = patientRepository
                .findById(patient_id).orElseThrow(()->
                        new NotFoundException("Patient with id: "+patient_id+", not found!!!")
                );

        Department department = appointment.getDepartment();

        List<Doctor> doctors_with_same_department = doctorRepository.findByDepartmentAndHospital(department,hospital_id);

        Doctor random_doctor = doctors_with_same_department.get(
                new Random().nextInt(doctors_with_same_department.size())
        );

        appointment.setDoctor(random_doctor);
        random_doctor.getAppointments().add(appointment);

        appointment.setPatient(patient);
        patient.getAppointments().add(appointment);

        hospital.getAppointments().add(appointment);


        return null;
    }

    @Override
    public Appointment getAppointmentById(String appointment_id) {
        return null;
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return null;
    }

    @Override
    public Appointment updateAppointment(String appointment_id, Appointment appointment) {
        return null;
    }

    @Override
    public String deleteAppointmentById(String appointment_id) {
        return null;
    }
}
