package com.cat.msa.invoices.domain;

import com.cat.msa.invoices.constant.Constant;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Table(name = "T_INVOICE_HEADERS")
@Entity
public class InvoiceHeader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INH_ID", nullable = false)
    private long id;

    @Column(name = "INH_NUMBER", nullable = false)
    private String number;

    @Column(name = "INH_CUS_NAME", nullable = false)
    private String customerName;

    @Column(name = "INH_DATE", nullable = false)
    private LocalDateTime date;

    @Column(name = "INH_SUB_TOTAL", nullable = false)
    private BigDecimal subtotalAmount;

    @Column(name = "INH_VAT_AMOUNT", nullable = false)
    private  BigDecimal vatAmount;

    @Column(name = "INH_TOTAL", nullable = false)
    private  BigDecimal totalAmount;

    @OneToMany( mappedBy="invoiceHeader", cascade = CascadeType.ALL)
    private List<InvoiceDetail> invoiceDetails;

    public void calculateInvoiceAmount(){
        calculateSubtotalAmount();
        calculateVatAmount();
        calculateTotalAmount();
        addInvoiceDetail();
    }

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

    public void addInvoiceDetail(){
        for(InvoiceDetail invoiceDetail: invoiceDetails){
            invoiceDetail.setInvoiceHeader(this);
        }
    }
}
