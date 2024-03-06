package com.niq.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.niq.java.dto.ProductResponse;
import com.niq.java.service.EcommerceService;

@RestController
@RequestMapping("/api/v1/ecommerce")
public class EcommerceController {

    private final EcommerceService ecommerceService;
    
    @Autowired
    public EcommerceController(EcommerceService ecommerceService) {
        this.ecommerceService = ecommerceService;
    }
    
    @GetMapping("{shopperId}")
    public ResponseEntity<List<ProductResponse>> getProductsByShopperId(@PathVariable String shopperId,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String brand,
            @RequestParam(defaultValue = "10") int limit) throws Exception {
        List<ProductResponse> products = ecommerceService.getProductsByShopperId(shopperId, category, brand, limit);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
