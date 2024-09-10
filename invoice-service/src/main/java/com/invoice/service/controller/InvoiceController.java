package com.invoice.service.controller;


import com.invoice.service.dto.InvoiceDTO;
import com.invoice.service.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @GetMapping("/invoices")
    public List<InvoiceDTO> getInvoices(@RequestParam("user_id") String userId) {
        log.info("Received request for userId : {} ", userId);

        return invoiceService.getInvoiceList(userId);
    }

}
