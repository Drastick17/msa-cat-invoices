package com.cat.msa.invoices.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Table(name = "T_INVOICE_DETAILS")
@Entity
public class InvoiceDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IND_ID", nullable = false)
    private  Long id;
    @Column(name = "IND_PROD_NAME", nullable = false)
    private  String productName;
    @Column(name = "IND_QUANTITY", nullable = false)
    private  Integer quantity;
    @Column(name = "IND_UNIT_PRICE", nullable = false)
    private BigDecimal unitPrice;
    @Column(name = "IND_SUBTOTAL", nullable = false)
    private BigDecimal subtotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="IND_INH_ID", nullable = false)
    @JsonIgnore
    private InvoiceHeader invoiceHeader;

    public void calculateSubtotal(){
        subtotal = unitPrice.multiply(new BigDecimal(quantity));
    }
}
