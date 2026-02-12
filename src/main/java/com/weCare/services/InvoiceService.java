package com.weCare.services;

import java.util.List;
import java.util.Map;

import com.weCare.modals.Invoice;

public interface InvoiceService {

	public Invoice generateInvoice(String prescription_id, Invoice invoice);

	public Map<String, Object> payMedicationCharges(String invoice_id, Invoice invoice);

	public Invoice getInvoiceById(String invoice_id);

	public List<Invoice> getAllInvoices();

	public Invoice updateInvoice(String invoice_id, Invoice invoice);

	public String deleteInvoiceById(String invoice_id);

}
