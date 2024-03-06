package com.niq.java.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.niq.java.dto.ProductResponse;
import com.niq.java.exception.ResourceNotFoundException;

@Service
public class EcommerceServiceImpl implements EcommerceService{
    
    @Autowired
    private RestTemplate restTemplate;
    private static final String GET_PRODUCTS_ENDPOINT_URL = "http://localhost:9090/api/v1/products/{shopperId}";
    
    public List<ProductResponse> getProductsByShopperId(String shopperId, String category, String brand, int limit) throws Exception {
        Map<String, String> uriParam = new HashMap<>();
        uriParam.put("shopperId", shopperId);
        UriComponents builder = buildUri(category, brand, limit);
        List<ProductResponse> products = null;
        try {
            products = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null, new ParameterizedTypeReference<List<ProductResponse>>() {}, uriParam).getBody();
        }
        catch(RestClientResponseException ex) {
            if (ex.getRawStatusCode() == 404) {
                throw new ResourceNotFoundException(ex.getMessage());
            }
            throw new Exception(ex.getMessage());
        }
        return products;
    }
    
    private UriComponents buildUri(String category, String brand, int limit) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(GET_PRODUCTS_ENDPOINT_URL);     
        if (category != null) {
            builder = builder.queryParam("category", category);
        }
        if (brand != null) {
            builder = builder.queryParam("brand", brand);
        }
        limit = limit > 100 ? 100 : limit;
        UriComponents uriComponentBuilder = builder.queryParam("limit", limit).build();
        return uriComponentBuilder;
    }
}