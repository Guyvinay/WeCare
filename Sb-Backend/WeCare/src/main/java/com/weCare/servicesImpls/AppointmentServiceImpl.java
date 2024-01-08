package com.weCare.servicesImpls;

import com.weCare.exceptions.NotFoundException;
import com.weCare.modals.*;
import com.weCare.repository.AppointmentRepository;
import com.weCare.repository.DoctorRepository;
import com.weCare.repository.HospitalRepository;
import com.weCare.repository.PatientRepository;
import com.weCare.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public Appointment bookAppointment(
                            Appointment appointment,
                            String hospital_id, String patient_id
                         ) {
        Hospital hospital = hospitalRepository
                             .findById(hospital_id).orElseThrow(()->
                                new NotFoundException("Hospital with id: "+hospital_id+", not found!!!")
                              );
        System.out.println(hospital);
        Patient patient = patientRepository
                .findById(patient_id).orElseThrow(()->
                        new NotFoundException("Patient with id: "+patient_id+", not found!!!")
                );
        System.out.println(patient);

        Department department = appointment.getDepartment();

        List<Doctor> doctors_with_same_department = doctorRepository
                                                          .findByDepartmentAndHospital(
                                                                  department,hospital_id);
        if(doctors_with_same_department.isEmpty())
            throw new NotFoundException("Doctors with:"+department+", not found");

        System.out.println(doctors_with_same_department);

        Doctor random_doctor = doctors_with_same_department.get(
                new Random().nextInt(doctors_with_same_department.size())
        );

        System.out.println(random_doctor);

        appointment.setAppointment_time(LocalDateTime.now());

        patient.setDoctor(random_doctor);
        patient.setAppointment_status(AppointmentStatus.SUCCESS);
        random_doctor.getPatients().add(patient);

        appointment.setDoctor(random_doctor);
        random_doctor.getAppointments().add(appointment);

        appointment.setPatient(patient);
        patient.getAppointments().add(appointment);

        appointment.setHospital(hospital);
        hospital.getAppointments().add(appointment);

        appointmentRepository.save(appointment);

        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment getAppointmentById(String appointment_id) {

        return appointmentRepository
                .findById(appointment_id).orElseThrow(()->
                        new NotFoundException("Doctor with id: "+appointment_id+", not found!!!")
                );
    }

    @Override
    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        if(appointments.isEmpty()) throw new NotFoundException("Appointment Not Found!!!");
        return appointments;
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
