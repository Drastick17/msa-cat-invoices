package com.cat.msa.invoices.service.impl;

import com.cat.msa.invoices.domain.InvoiceHeader;
import com.cat.msa.invoices.exception.NotContentException;
import com.cat.msa.invoices.exception.NotDuplicateNumberException;
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
        InvoiceHeader duplicateInvoiceNumber = this.findByNumber(invoiceHeader.getNumber());
        if(duplicateInvoiceNumber != null) throw new NotDuplicateNumberException("No se puede repetir el mismo numero de factura");
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

    @Override
    public InvoiceHeader findByNumber(String number) {
        InvoiceHeader invoiceHeader = invoiceHeaderRepository.findByNumber(number);
        if(invoiceHeader == null){
            throw new NotContentException("Not content");
        } else {
            return invoiceHeader;
        }
    }

    @Override
    public void deleteByNumber(String number) {
        InvoiceHeader invoiceHeader = this.findByNumber(number);
        invoiceHeaderRepository.delete(invoiceHeader);
    }
}
