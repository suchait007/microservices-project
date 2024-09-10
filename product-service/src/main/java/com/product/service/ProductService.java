package com.product.service;


import com.api.gateway.dto.ProductDTO;
import com.api.gateway.dto.ProductRequest;
import com.product.entities.Product;
import com.product.jpa.ProductRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepo productRepo;


    public void storeAllProducts(ProductRequest productRequest) {

        List<ProductDTO> productDTOList = productRequest.getProductDTOList();

        List<Product> products =
                productDTOList.stream()
                        .map(this::getProduct)
                        .toList();

        productRepo.saveAll(products);
    }


    private Product getProduct(ProductDTO productDTO) {

        if (productDTO == null) {
            return null;
        }

        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStockQuantity(productDTO.getStockQuantity());

        return product;
    }

    @Cacheable(value = "products", key = "#ids")
    public List<ProductDTO> getProducts(List<String> ids) {


        log.info("Calling database to get ids: {} ", ids);
        List<Product> products = productRepo.findAllByProductIdIn(ids);


        List<ProductDTO> productDTOList = products.stream()
                .map(this::getProductDTO)
                .toList();

        if(CollectionUtils.isEmpty(productDTOList)) {
            return Collections.EMPTY_LIST;
        }

        return productDTOList;
    }

    private ProductDTO getProductDTO(Product product) {

        if (product == null) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setStockQuantity(product.getStockQuantity());

        return productDTO;
    }
}
