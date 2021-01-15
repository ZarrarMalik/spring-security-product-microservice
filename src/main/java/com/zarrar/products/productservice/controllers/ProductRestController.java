package com.zarrar.products.productservice.controllers;

import com.zarrar.products.productservice.dto.Coupon;
import com.zarrar.products.productservice.model.Product;
import com.zarrar.products.productservice.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/productapi")
public class ProductRestController {
   @Autowired
    private ProductRepo repo;

    @Autowired

    private RestTemplate restTemplate;

    @Value("${couponService.url}")
    private String couponServiceURL;

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public Product create( @RequestBody Product product){
        Coupon coupon = restTemplate.getForObject(couponServiceURL + product.getCouponCode(), Coupon.class);
        product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
        return repo.save(product);
    }



}
