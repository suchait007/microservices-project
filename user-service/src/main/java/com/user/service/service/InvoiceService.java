package com.user.service.service;


import com.user.service.client.InvoiceServiceFeignClient;
import com.user.service.dto.InvoiceDTO;
import com.user.service.exception.InvoiceException;
import com.user.service.exception.UserException;
import feign.FeignException.ServiceUnavailable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@RequiredArgsConstructor
@Service
public class InvoiceService {

    private final InvoiceServiceFeignClient invoiceServiceFeignClient;

    private final RetryTemplate retryTemplate;


    //@Retryable(retryFor = {Exception.class}, maxAttempts = 4, backoff = @Backoff(delay = 3000))
    public List<InvoiceDTO> callInvoiceServiceAndGetInvoiceDTOList(String userId) {

        AtomicReference<List<InvoiceDTO>> invoiceResponse = new AtomicReference<>(new ArrayList<>());

        try {

            retryTemplate.execute(arg -> {
                invoiceResponse.set(invoiceServiceFeignClient.getInvoices(userId));
                return invoiceResponse;
            });

            //invoiceResponse = invoiceServiceFeignClient.getInvoices(userId);

        } catch (InvoiceException invoiceException) {
            throw invoiceException;
        } catch (ServiceUnavailable ex) {
            throw new UserException("Downstream service unavailable", List.of("invoice-service is down"), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            throw new UserException("Downstream service unavailable", List.of("invoice-service is down"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return invoiceResponse.get();
    }


}
