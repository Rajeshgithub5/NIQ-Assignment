package com.niq.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niq.java.entity.ProductMetadata;

public interface ProductMetdataRepository extends JpaRepository<ProductMetadata, String> {

}
