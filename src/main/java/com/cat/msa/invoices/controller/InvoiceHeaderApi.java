package com.cat.msa.invoices.controller;

import com.cat.msa.invoices.domain.InvoiceHeader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/invoice-headers")
public interface InvoiceHeaderApi {

    @PostMapping
    ResponseEntity<InvoiceHeader> save(@RequestBody InvoiceHeader invoiceHeader);



}
