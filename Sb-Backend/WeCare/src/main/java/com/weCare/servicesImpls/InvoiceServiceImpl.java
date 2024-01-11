package com.weCare.servicesImpls;

import java.util.List;

import com.weCare.exceptions.PrescriptionNotFoundException;
import com.weCare.modals.Invoice;
import com.weCare.modals.Medication;
import com.weCare.modals.Prescription;
import com.weCare.repository.InvoiceRepository;
import com.weCare.repository.PrescriptionRepository;
import com.weCare.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Override
    public Invoice generateInvoice(String prescription_id, Invoice invoice) {
        Prescription prescription = prescriptionRepository.findById(prescription_id)
                .orElseThrow(()->
                         new PrescriptionNotFoundException("Prescription with id:"+prescription_id+", not found!!!")
                );
//        List<Medication> prescription_medications =  prescription.getMedications();
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
