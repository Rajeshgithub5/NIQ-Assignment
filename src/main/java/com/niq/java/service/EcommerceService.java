package com.niq.java.service;

import java.util.List;

import com.niq.java.dto.ProductResponse;

public interface EcommerceService {

    public List<ProductResponse> getProductsByShopperId(String shopperId, String category, String brand, int limit) throws Exception;
}
