package com.weCare.services;

import java.util.List;

import com.weCare.modals.Invoice;

public interface InvoiceService {

    public Invoice generateInvoice(Invoice invoice);

    public Invoice getInvoiceById(String invoice_id);

    public List<Invoice> getAllInvoices();

    public Invoice updateInvoice(String invoice_id, Invoice invoice);

    public String deleteInvoiceById(String invoice_id);
    
}
