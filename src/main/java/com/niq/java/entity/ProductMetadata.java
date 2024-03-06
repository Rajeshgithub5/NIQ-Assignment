package com.niq.java.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "product_metadata")
public class ProductMetadata {

    @Id
    @Column(name = "product_id")
    private String productId;

    @Column(name = "category")
    private String category;

    @Column(name = "brand")
    private String brand;
}
