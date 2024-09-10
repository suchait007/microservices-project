package com.product.jpa;

import com.product.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, String> {

    List<Product> findAllByProductIdIn(List<String> productIds);
}
