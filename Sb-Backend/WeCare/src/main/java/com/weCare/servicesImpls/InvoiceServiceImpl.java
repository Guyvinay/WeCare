package com.weCare.servicesImpls;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weCare.exceptions.InvoiceNotFoundException;
import com.weCare.exceptions.MedicationNotFoundException;
import com.weCare.exceptions.PrescriptionNotFoundException;
import com.weCare.modals.Invoice;
import com.weCare.modals.Medication;
import com.weCare.modals.PaymentStatus;
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
      
        Double total_medication_price = 0.0;
        
        //Getting invoice_medications From invoice
        Map<String,Integer> invoice_medications = invoice.getMedications_invoice();
        
      //Getting prescription_medication From Prescription
        Map<String,Integer> prescription_medication 
                                          = prescription.getMedication_ids();

        //Merging prescription_medication with invoice_medications
        prescription_medication.putAll(invoice_medications);
        invoice_medications.putAll(prescription_medication);
        
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
        	List<String> missing_medication_ids = 
        			new ArrayList<>(expected_medication_ids_list);
        	missing_medication_ids.removeAll(retrieved_medication_ids_list);
	
        	throw new MedicationNotFoundException(
        			"Medications with id: "+
        	String.join(", ", missing_medication_ids)+
        	", not available!!!"
        			);
        }
        
        
        for( Medication medication : retrieved_medications ) {
        	
        	Integer expected_medication_quantity = 
        			prescription_medication.get(medication.getMedication_id());
        	
        	Integer available_medication_quantity = 
        			medication.getMedication_quantity();
        	
        	if(expected_medication_quantity>available_medication_quantity)
        		throw new MedicationNotFoundException(
        				"You need "+
        						expected_medication_quantity+
        						" medications of "+
        						medication.getMedication_id()+
        						", but only "+
        						available_medication_quantity+
        						" medications available!!!"
        				);
        	
              total_medication_price +=
    		         medication.getMedication_price()*expected_medication_quantity;
        	
        	medication.setMedication_quantity(
        			available_medication_quantity-expected_medication_quantity
        			);
        }
        
        prescription.getInvoices().add(invoice);

        total_medication_price = (double) (Math.round(total_medication_price*100)/100);
        
        invoice.setTotal_amount(total_medication_price);
        invoice.setPrescription(prescription);
        invoice.setInvoice_date_time(LocalDateTime.now());
//        invoice.setMedications_invoice(prescription_medication);
        invoice.setMedications(retrieved_medications);
        invoice.setPaymentStatus(PaymentStatus.PAYMENT_PENDING);
        
//        return invoice;
        return invoiceRepository.save(invoice);
    }

    @Override
	public Map<String, Object> payMedicationCharges(String invoice_id, Invoice invoice) {
		Map<String, Object> invoice_details = new HashMap<>();
    	Invoice retrieved_invoice = invoiceRepository.findById(invoice_id)
		    			.orElseThrow(()-> 
		    	              new InvoiceNotFoundException(
					    		  "Invoice with id: "+invoice_id+", not found!!!")
					    			 );
    	
    	Double amount_paying = invoice.getTotal_amount();
    	Double amount_to_be_paid = retrieved_invoice.getTotal_amount();
    	
    	retrieved_invoice.setInvoice_date_time(LocalDateTime.now());
    	retrieved_invoice.setPaymentStatus(PaymentStatus.PAYMENT_SUCCESS);
    	
    	
    	if(amount_paying>amount_to_be_paid) {
    		Invoice updated_invoice = invoiceRepository.save(retrieved_invoice);
    		invoice_details.put(
    				"message", 
    				"Thanks for using our service, Kindly collect your " +
    				(amount_paying-amount_to_be_paid)
    				);
    		
    		invoice_details.put("invoice", updated_invoice);
    		return invoice_details;
    	}else if(amount_paying<amount_to_be_paid) {
    		throw new InvoiceNotFoundException(
    				"You are paying "+
    		          amount_paying+
    		          ", but it costs "+
    		          amount_to_be_paid+
    		          " !!!"
    				);
    	}
    	
//    	return invoice_details;
    	Invoice updated_invoice = invoiceRepository.save(retrieved_invoice);
    	invoice_details.put(
    			"message", 
    			"Thank you purchasing with us, collect your medications....");
    	invoice_details.put("invoice", updated_invoice);
		return invoice_details;
	}
    
    @Override
    public Invoice getInvoiceById(String invoice_id) {

        return invoiceRepository.findById(invoice_id)
    			.orElseThrow(()-> 
	              new InvoiceNotFoundException(
		    		  "Invoice with id: "+invoice_id+", not found")
		    			 );
    }

    @Override
    public List<Invoice> getAllInvoices() {
    	List<Invoice> invoices = invoiceRepository.findAll();
    	if(invoices.isEmpty())
    		throw new InvoiceNotFoundException("Invoices not found!!!");
        return invoices;
    			
    }

    @Override
    public Invoice updateInvoice(String invoice_id, Invoice invoice) {
        return invoice;
    }

    @Override
    public String deleteInvoiceById(String invoice_id) {
    	Invoice invoice = invoiceRepository.findById(invoice_id)
								.orElseThrow(()-> 
						          new InvoiceNotFoundException(
						    		  "Invoice with id: "+invoice_id+", not found")
						    			 );
    	invoiceRepository.delete(invoice);
        return "invoice with id: "+invoice_id+", deleted!!!";
    }

	
}
