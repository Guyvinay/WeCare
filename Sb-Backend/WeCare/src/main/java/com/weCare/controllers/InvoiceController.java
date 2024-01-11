package com.weCare.controllers;

import com.weCare.modals.Invoice;
import com.weCare.services.InvoiceService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/invoices")
public class InvoiceController {

	@Autowired
	private InvoiceService invoiceService;

	@PostMapping(value = "/{prescription_id}")
	public ResponseEntity<Invoice> generateInvoice(
			@PathVariable("prescription_id") String prescription_id,
			@RequestBody Invoice invoice
			){
		return new ResponseEntity<Invoice>(
				invoiceService.generateInvoice(prescription_id,invoice),
				HttpStatus.ACCEPTED
		);
	}
	@GetMapping(value = "/{invoice_id}")
	public ResponseEntity<Invoice> getInvoiceById(
			@PathVariable("invoice_id") String invoice_id){
		return new ResponseEntity<Invoice>(
				invoiceService.getInvoiceById(invoice_id),
				HttpStatus.ACCEPTED
		);
	}
	@GetMapping()
	public ResponseEntity<List<Invoice>> getAllInvoices(){
		return new ResponseEntity<List<Invoice>>(
				invoiceService.getAllInvoices(),
				HttpStatus.ACCEPTED
		);
	}
	@PutMapping(value = "/{invoice_id}")
	public ResponseEntity<Invoice> updateInvoiceById(
			@PathVariable("invoice_id") String invoice_id,
			@RequestBody Invoice invoice
			){
		return new ResponseEntity<Invoice>(
				invoiceService.updateInvoice(invoice_id,invoice),
				HttpStatus.ACCEPTED
		);
	}
	@DeleteMapping(value = "/{invoice_id}")
	public ResponseEntity<String> deleteInvoiceById(
			@PathVariable("invoice_id") String invoice_id){

		return new ResponseEntity<String>(
				invoiceService.deleteInvoiceById(invoice_id),
				HttpStatus.ACCEPTED
		);
	}
}
