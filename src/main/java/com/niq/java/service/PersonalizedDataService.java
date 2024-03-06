package com.niq.java.service;

import java.util.List;

import com.niq.java.dto.ProductMetdataRequest;
import com.niq.java.dto.ProductRequest;
import com.niq.java.dto.ProductResponse;
import com.niq.java.entity.ProductMetadata;
import com.niq.java.entity.Shopper;

public interface PersonalizedDataService {

    public Shopper addProducts(ProductRequest products);
    public ProductMetadata addProductMetaData(ProductMetdataRequest productMetadata);
    public List<ProductResponse> getProductsByShopperId(String shopperId, String category, String brand, int limit);
}
