package com.niq.java.dto;

import java.util.List;

import com.niq.java.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private String shopperId;
    private List<Product> shelf;
}