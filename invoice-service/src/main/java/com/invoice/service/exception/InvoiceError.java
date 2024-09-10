package com.invoice.service.exception;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@AllArgsConstructor
@Data
public class InvoiceError {

    private String errorMessage;
    private List<String> errorDetails;
}
