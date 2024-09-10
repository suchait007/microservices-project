package com.user.service.dto;


import lombok.Data;

@Data
public class InvoiceDTO {

    private String invoiceId;
    private String invoiceNumber;
    private String productIds;
    private String updatedTime;;
}
