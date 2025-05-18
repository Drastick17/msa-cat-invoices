package com.cat.msa.invoices.service.impl;

import com.cat.msa.invoices.domain.InvoiceHeader;
import com.cat.msa.invoices.exception.NotContentException;
import com.cat.msa.invoices.repository.InvoiceHeaderRepository;
import com.cat.msa.invoices.service.InvoiceHeaderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceHeaderServiceImpl implements InvoiceHeaderService {
    private final InvoiceHeaderRepository invoiceHeaderRepository;

    public InvoiceHeaderServiceImpl(InvoiceHeaderRepository invoiceHeaderRepository) {
        this.invoiceHeaderRepository = invoiceHeaderRepository;
    }


    @Override
    public InvoiceHeader createInvoiceHeader(InvoiceHeader invoiceHeader) {
        invoiceHeader.calculateInvoiceAmount();
        return invoiceHeaderRepository.save(invoiceHeader);
    }

    @Override
    public List<InvoiceHeader> getAll() {
        List<InvoiceHeader> invoiceHeaders = invoiceHeaderRepository.findAll();
        if(invoiceHeaders.isEmpty()){
            throw new NotContentException("Not content");
        } else {
            return invoiceHeaders;
        }
    }
}
