package com.weCare.services;

import com.weCare.modals.Medication;

import java.util.List;

public interface MedicationService {

    public Medication saveMedication(Medication medication);
    
    public List<Medication> saveMedications(List<Medication> medications);

    public Medication getMedicationById(String medication_id);

    public List<Medication> getAllMedications();

    public Medication updateMedication(String medication_id, Medication medication);

    public String deleteMedicationById(String medication_id);


}
