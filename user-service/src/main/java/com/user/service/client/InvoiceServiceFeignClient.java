package com.user.service.client;


import com.user.service.dto.InvoiceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(url = "${invoice.service.url}", value = "invoice-service-feign-client", path = "/v1")
public interface InvoiceServiceFeignClient {

    @GetMapping("/invoices")
     List<InvoiceDTO> getInvoices(@RequestParam("user_id") String userId);


}
