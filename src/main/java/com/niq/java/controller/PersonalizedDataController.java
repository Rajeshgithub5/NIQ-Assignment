package com.niq.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.niq.java.dto.ProductMetdataRequest;
import com.niq.java.dto.ProductRequest;
import com.niq.java.dto.ProductResponse;
import com.niq.java.entity.ProductMetadata;
import com.niq.java.entity.Shopper;
import com.niq.java.service.PersonalizedDataServiceImpl;

@RestController
@RequestMapping("/api/v1/products")
public class PersonalizedDataController {

    @Autowired
    private PersonalizedDataServiceImpl personalizedDataServiceImpl;
    
    @PostMapping
    public ResponseEntity<Shopper> addProducts(@RequestBody ProductRequest productRequest) {
        Shopper shopper = personalizedDataServiceImpl.addProducts(productRequest);
        return new ResponseEntity<>(shopper, HttpStatus.CREATED);
    }
    
    @PostMapping("/metadata")
    public ResponseEntity<ProductMetadata> addProductMetadata(@RequestBody ProductMetdataRequest metadataRequest) {
        ProductMetadata metdata = personalizedDataServiceImpl.addProductMetaData(metadataRequest);
        return new ResponseEntity<>(metdata, HttpStatus.CREATED);
    }
    
    @GetMapping("{shopperId}")
    public ResponseEntity<List<ProductResponse>> getProductsByShopperId(@PathVariable String shopperId,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String brand,
            @RequestParam(defaultValue = "10") int limit) {
        List<ProductResponse> products = personalizedDataServiceImpl.getProductsByShopperId(shopperId, category, brand, limit);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
