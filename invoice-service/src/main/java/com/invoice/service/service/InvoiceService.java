package com.invoice.service.service;

import com.invoice.service.dto.InvoiceDTO;
import com.invoice.service.entities.Invoice;
import com.invoice.service.exception.InvoiceException;
import com.invoice.service.repos.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public List<InvoiceDTO> getInvoiceList(String userId) {

        if(StringUtils.isEmpty(userId)) {
            throw new InvoiceException(HttpStatus.BAD_REQUEST, "Bad request", List.of("userId is missing in request."));
        }

        log.info("Calling Database with userId: {} ", userId);
        List<Invoice> invoices = invoiceRepository.findAllByUserId(userId);


        log.info("Returning invoice-service response");
        return invoices.stream()
                .map(this::populateInvoiceDTO)
                .toList();

    }

    private InvoiceDTO populateInvoiceDTO(Invoice invoice) {

        InvoiceDTO dto = new InvoiceDTO();

        dto.setInvoiceId(invoice.getId());
        dto.setInvoiceNumber(invoice.getInvoiceNumber());
        dto.setProductIds(invoice.getProductIds());
        dto.setUpdatedTime(invoice.getUpdatedTime().toString());


        return dto;
    }
}
