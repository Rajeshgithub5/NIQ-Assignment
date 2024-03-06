package com.niq.java.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    
    private String productId;
    private double relevancyScore;
    private String brand; 
    private String category;
}
