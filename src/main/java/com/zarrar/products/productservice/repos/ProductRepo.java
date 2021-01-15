package com.zarrar.products.productservice.repos;

import com.zarrar.products.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepo extends JpaRepository<Product, Long> {

}