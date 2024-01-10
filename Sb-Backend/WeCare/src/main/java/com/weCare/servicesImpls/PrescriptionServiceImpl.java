package com.weCare.servicesImpls;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weCare.exceptions.AppointmentNotFoundException;
import com.weCare.exceptions.PrescriptionNotFoundException;
import com.weCare.modals.Appointment;
import com.weCare.modals.AppointmentStatus;
import com.weCare.modals.Doctor;
import com.weCare.modals.Patient;
import com.weCare.modals.Prescription;
import com.weCare.repository.AppointmentRepository;
import com.weCare.repository.PrescriptionRepository;
import com.weCare.services.PrescriptionService;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Override
    public Prescription generatePrescription(String appointment_id, Prescription prescription) {

        Appointment appointment = appointmentRepository
                .findById(appointment_id).orElseThrow(()->
                        new AppointmentNotFoundException("Hospital with id: "+appointment_id+", not found!!!")
                );
        appointment.setStatus(AppointmentStatus.APPOINTMENT_COMPLETED);
        appointment.setPrescription(prescription);
        
        //Getting Patient from appointment
        Patient patient = appointment.getPatient();
        patient.getPrescriptions().add(prescription);

        
        //Getting Doctor from appointment
        Doctor doctor = appointment.getDoctor();
        doctor.getPrescriptions().add(prescription);

        //Prescription Persistence
        prescription.setPrescription_date(LocalDateTime.now());
        prescription.setAppointment(appointment);
        prescription.setDoctor(doctor);
        prescription.setPatient(patient);
        
        return prescriptionRepository.save(prescription);
    }

    @Override
    public Prescription getPrescriptionById(String prescription_id) {

        return prescriptionRepository
                .findById(prescription_id).orElseThrow(()->
                        new AppointmentNotFoundException("Prescription with id: "+prescription_id+", not found!!!")
                );
    }

    @Override
    public List<Prescription> getAllPrescriptions() {
        List<Prescription> prescriptions = prescriptionRepository.findAll();
        if(prescriptions.isEmpty())
            throw new PrescriptionNotFoundException("Prescriptions not found!!!");
        return prescriptions;
    }

    @Override
    public Prescription updatePrescription(String prescription_id, Prescription prescription) {
        return null;
    }

    @Override
    public String deletePrescriptionById(String prescription_id) {
        Prescription prescription = prescriptionRepository
                .findById(prescription_id).orElseThrow(()->
                        new AppointmentNotFoundException("Prescription with id: "+prescription_id+", not found!!!")
                );
        prescriptionRepository.delete(prescription);
        return "Prescription with id: "+prescription_id+", deleted";
    }
}
