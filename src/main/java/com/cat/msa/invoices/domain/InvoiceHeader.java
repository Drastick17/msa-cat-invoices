package com.cat.msa.invoices.domain;

import com.cat.msa.invoices.constant.Constant;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class InvoiceHeader {
    private long id;
    private String number;
    private String customerName;
    private String date;
    private BigDecimal subtotalAmount;
    private  BigDecimal vatAmount;
    private  BigDecimal totalAmount;
    private List<InvoiceDetail> invoiceDetails;

    public void calculateSubtotalAmount(){
        BigDecimal subtotalAmount = BigDecimal.ZERO;
        for(InvoiceDetail invoiceDetail : invoiceDetails){
            invoiceDetail.calculateSubtotal();
            subtotalAmount = subtotalAmount.add(invoiceDetail.getSubtotal());
        }
    }

    public void calculateVatAmount(){
        vatAmount = subtotalAmount.multiply(Constant.VAT_RATE);
    }
    public void calculateTotalAmount(){
        totalAmount = subtotalAmount.add(vatAmount);
    }


}
