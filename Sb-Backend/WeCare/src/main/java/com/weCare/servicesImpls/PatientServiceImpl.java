package com.weCare.servicesImpls;

import com.weCare.exceptions.NotFoundException;
import com.weCare.modals.Doctor;
import com.weCare.modals.Patient;
import com.weCare.repository.PatientRepository;
import com.weCare.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Patient savePatient(Patient patient) {

        return patientRepository.save(patient);
    }

    @Override
    public Patient getPatientById(String patient_id) {

        return patientRepository
                .findById(patient_id).orElseThrow(()->
                        new NotFoundException("Hospital with id: "+patient_id+", not found!!!")
                );
    }

    @Override
    public List<Patient> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        if(patients.isEmpty())throw new NotFoundException("No Patients Found!!!");
        return patients;
    }

    @Override
    public Patient updatePatient(String patient_id, Patient patient) {
        return null;
    }

    @Override
    public String deletePatientById(String patient_id) {
        Patient patient = patientRepository
                .findById(patient_id).orElseThrow(()->
                        new NotFoundException("Hospital with id: "+patient_id+", not found!!!")
                );
        patientRepository.delete(patient);
        return "Patient with id: "+patient_id+", deleted successfully";
    }
}
