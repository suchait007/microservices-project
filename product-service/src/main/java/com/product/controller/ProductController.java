package com.product.controller;


import com.api.gateway.dto.ProductDTO;
import com.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class ProductController {

    private final ProductService productService;


    @GetMapping("/products")
    public List<ProductDTO> getProducts(@RequestParam("ids") List<String> productIds) {
        return productService.getProducts(productIds);
    }

}
