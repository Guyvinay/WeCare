package com.weCare.servicesImpls;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weCare.exceptions.MedicationNotFoundException;
import com.weCare.exceptions.PrescriptionNotFoundException;
import com.weCare.modals.Invoice;
import com.weCare.modals.Medication;
import com.weCare.modals.Prescription;
import com.weCare.repository.InvoiceRepository;
import com.weCare.repository.MedicationRepository;
import com.weCare.repository.PrescriptionRepository;
import com.weCare.services.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;
    
    @Autowired
    private MedicationRepository medicationRepository;

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Override
    public Invoice generateInvoice(String prescription_id, Invoice invoice) {
    	
    	//Getting Prescription From PrescriptionRepository
        Prescription prescription = prescriptionRepository.findById(prescription_id)
                .orElseThrow(()->
                         new PrescriptionNotFoundException("Prescription with id:"+prescription_id+", not found!!!")
                );
      //Getting prescription_medication From Prescription
        Map<String,Integer> prescription_medication 
                                          = prescription.getMedication_ids();
        
        //Getting invoice_medications From invoice
        Map<String,Integer> invoice_medications = invoice.getMedications_invoice();

        //Merging prescription_medication with invoice_medications
        prescription_medication.putAll(invoice_medications);
        
        //Getting expected_medication_ids_list
        List<String> expected_medication_ids_list = new
        		                ArrayList<>(prescription_medication.keySet());
        
        //Getting valid Medications From medicationRepository;
        List<Medication> retrieved_medications = medicationRepository
        		                 .findByMedication_idIn(expected_medication_ids_list);
        
        //Getting valid Medications ids From retrieved_medications;
        List<String> retrieved_medication_ids_list = retrieved_medications.stream()
							        		.map(Medication::getMedication_id)
							        		.collect(Collectors.toList());
        
        
        //Throwing exception with missing medications id;
        if(expected_medication_ids_list.size()!=retrieved_medication_ids_list.size()) {
        	
        	//Creating array with expected_medication_ids_list and removing retrieved_medication_ids_list
        	List<String> missing_medication_ids = new ArrayList<>(expected_medication_ids_list);
        	missing_medication_ids.removeAll(retrieved_medication_ids_list);
        	
//        	missing_medication_ids.forEach(System.out::println);
        	
        	throw new MedicationNotFoundException(
        			"Medications with id: "+
        	String.join(", ", missing_medication_ids)+
        	", not available!!!"
        			);
        }
        
        
        
        
        
//        System.out.println(retrieved_medications);
        retrieved_medications.forEach(System.out::println);
//        System.out.println(prescription_medication);
        invoice.setMedications_invoice(prescription_medication);
        return invoice;
    }

    @Override
    public Invoice getInvoiceById(String invoice_id) {
        return null;
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return null;
    }

    @Override
    public Invoice updateInvoice(String invoice_id, Invoice invoice) {
        return null;
    }

    @Override
    public String deleteInvoiceById(String invoice_id) {
        return null;
    }
}
