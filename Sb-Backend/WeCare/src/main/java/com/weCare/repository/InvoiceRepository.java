package com.weCare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weCare.modals.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, String>{

}
