package com.cat.msa.invoices.controller;

import com.cat.msa.invoices.domain.InvoiceHeader;
import com.cat.msa.invoices.service.InvoiceHeaderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvoiceHeaderController implements InvoiceHeaderApi{

    private final InvoiceHeaderService invoiceHeaderService;

    public InvoiceHeaderController(InvoiceHeaderService invoiceHeaderService) {
        this.invoiceHeaderService = invoiceHeaderService;
    }

    @Override
    public ResponseEntity<InvoiceHeader> save(InvoiceHeader invoiceHeader) {
        invoiceHeader.calculateInvoiceAmount();
        InvoiceHeader savedInvoiceHeader = invoiceHeaderService.createInvoiceHeader(invoiceHeader);
        return new ResponseEntity<>(savedInvoiceHeader,HttpStatus.CREATED);
    }
}
