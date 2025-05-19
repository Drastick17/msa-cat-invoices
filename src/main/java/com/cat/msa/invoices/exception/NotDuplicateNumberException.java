package com.cat.msa.invoices.exception;

public class NotDuplicateNumberException extends RuntimeException{
    public NotDuplicateNumberException(String message) {
        super(message);
    }
}
