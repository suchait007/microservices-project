package com.invoice.service.exception;


import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class InvoiceException extends RuntimeException {

    private HttpStatus httpStatus;
    private String errorMsg;
    private List<String> errorDetails;

    public InvoiceException(HttpStatus httpStatus, String errorMsg, List<String> errorDetails) {
        super(errorMsg);
        this.httpStatus = httpStatus;
        this.errorMsg = errorMsg;
        this.errorDetails = errorDetails;
    }
}
