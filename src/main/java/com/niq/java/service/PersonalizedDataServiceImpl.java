package com.niq.java.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.niq.java.dto.ProductMetdataRequest;
import com.niq.java.dto.ProductRequest;
import com.niq.java.dto.ProductResponse;
import com.niq.java.entity.ProductMetadata;
import com.niq.java.entity.Shopper;
import com.niq.java.exception.ResourceNotFoundException;
import com.niq.java.repository.ProductMetdataRepository;
import com.niq.java.repository.ProductRepository;
import com.niq.java.repository.ShopperRepository;

@Service
@Transactional
public class PersonalizedDataServiceImpl implements PersonalizedDataService {

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private ShopperRepository shopperRepository;
    
    @Autowired
    private ProductMetdataRepository productMetdataRepository;
    
    public Shopper addProducts(ProductRequest products) {
        Shopper shopper = new Shopper();
        shopper.setShopperId(products.getShopperId());
        shopper.setShelf(products.getShelf());
        return shopperRepository.save(shopper);
    }
    
    public ProductMetadata addProductMetaData(ProductMetdataRequest productMetadata) {
        ProductMetadata metaData = new ProductMetadata();
        metaData.setBrand(productMetadata.getBrand());
        metaData.setCategory(productMetadata.getCategory());
        metaData.setProductId(productMetadata.getProductId());
        return productMetdataRepository.save(metaData);
    }
    
    @Cacheable(value = "products", key = "#shopperId")
    public List<ProductResponse> getProductsByShopperId(String shopperId, String category, String brand, int limit) {
        List<Object[]> products = productRepository.getProductsWithFilters(shopperId, category, brand);
        if (products == null || products.size() == 0) {
            throw new ResourceNotFoundException("Products not found with shopper id: "+shopperId);
        }
        List<ProductResponse> productsResponse = new ArrayList<>();
        for (Object[] product : products) {
            productsResponse.add(transformToProductResponse(product));
        }
        productsResponse = productsResponse.stream().limit(limit).collect(Collectors.toList());
        return productsResponse;
    }
    
    private ProductResponse transformToProductResponse(Object[] product) {
        int i = 0;
        String productId = product[i++].toString();
        double relevancyScore = (double)product[i++];
        String brand = product[i++].toString();
        String category = product[i++].toString();
        return new ProductResponse(productId, relevancyScore, brand, category);
    }
}
