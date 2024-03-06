package com.niq.java.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductMetdataRequest {
    
    private String productId;
    private String category;
    private String brand;
}
