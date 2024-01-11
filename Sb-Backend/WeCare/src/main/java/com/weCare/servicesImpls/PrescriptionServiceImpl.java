package com.weCare.servicesImpls;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weCare.exceptions.AppointmentNotFoundException;
import com.weCare.exceptions.MedicationNotFoundException;
import com.weCare.exceptions.PrescriptionNotFoundException;
import com.weCare.modals.Appointment;
import com.weCare.modals.AppointmentStatus;
import com.weCare.modals.Doctor;
import com.weCare.modals.Medication;
import com.weCare.modals.Patient;
import com.weCare.modals.Prescription;
import com.weCare.repository.AppointmentRepository;
import com.weCare.repository.MedicationRepository;
import com.weCare.repository.PrescriptionRepository;
import com.weCare.services.PrescriptionService;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;
    
    @Autowired
    private MedicationRepository medicationRepository;

    @Override
    public Prescription generatePrescription(String appointment_id, Prescription prescription) {

    	//Finding Appointment with appointment_id
        Appointment appointment = appointmentRepository
                .findById(appointment_id).orElseThrow(()->
                        new AppointmentNotFoundException("Appointment with id: "+appointment_id+", not found!!!")
                );
        
        Map<String,Integer> expected_medication_ids_map = prescription.getMedication_ids();
        
        List<String> expected_medication_ids_list = new ArrayList<>(expected_medication_ids_map.keySet());
     
        //Retrieved Medications List
        List<Medication> retrived_medications_list = medicationRepository.findByMedication_idIn(expected_medication_ids_list);

        List<String> retrived_medication_ids_list = retrived_medications_list.stream()
        		.map(Medication::getMedication_id).collect(Collectors.toList());
        
        //Throwing Exception with missing medications;
        if(expected_medication_ids_list.size()!=retrived_medication_ids_list.size()) {
        	
        	List<String> missing_medications_ids = new ArrayList<>(expected_medication_ids_list);
        	missing_medications_ids.removeAll(retrived_medication_ids_list);
        	
        	throw new MedicationNotFoundException(
        			"Medications with id: "+
        	String.join(", ", missing_medications_ids)+
        	", not available!!!"
        			);
        }
        
        
        
        /*
         
         
        for(Medication med : retrived_medications_list) {
//        	System.out.println(med.getMedication_price());
        	Integer extected_meds_quantity = expected_medication_ids_map.get(med.getMedication_id());
        	Integer available_medication_quantity = med.getMedication_quantity();
        	if(available_medication_quantity<extected_meds_quantity)
        		throw new MedicationNotFoundException(
        				"You need "+
        						extected_meds_quantity+
        						" medications of "+
        						med.getMedication_id()+
        						", but only"+
        						available_medication_quantity+
        						" medications available!!!"
        				);        	
        	med.setMedication_quantity(available_medication_quantity-extected_meds_quantity);
//        	System.out.println(med.getMedication_quantity());
        	
        }
        
        */
        
        
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
        prescription.setMedications(retrived_medications_list);
        
        	
//        return prescription;
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
