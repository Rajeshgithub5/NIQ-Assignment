package com.niq.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niq.java.entity.Shopper;

public interface ShopperRepository extends JpaRepository<Shopper, String> {

}
