package com.weCare.servicesImpls;

import com.weCare.exceptions.MedicationNotFoundException;
import com.weCare.modals.Medication;
import com.weCare.repository.MedicationRepository;
import com.weCare.services.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicationServiceImpl implements MedicationService {

    @Autowired
    private MedicationRepository medicationRepository;

    @Override
    public Medication saveMedication(Medication medication) {

        return medicationRepository.save(medication);
    }

    @Override
    public Medication getMedicationById(String medication_id) {

        return medicationRepository.findById(medication_id).orElseThrow(()->
                        new MedicationNotFoundException("Medication with id:"+medication_id+", not found")
                    );
    }

    @Override
    public List<Medication> getAllMedications() {
        List<Medication> medications =  medicationRepository.findAll();
        if(medications.isEmpty())
            throw new MedicationNotFoundException("Medications not found!!!");
        return medications;
    }

    @Override
    public Medication updateMedication(String medication_id, Medication medication) {
        return null;
    }

    @Override
    public String deleteMedicationById(String medication_id) {
        Medication medication =  medicationRepository.findById(medication_id).orElseThrow(()->
                new MedicationNotFoundException("Medication with id:"+medication_id+", not found")
        );
        medicationRepository.delete(medication);
        return "Medication: "+medication_id+" deleted";
    }
}
