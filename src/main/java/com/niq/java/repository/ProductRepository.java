package com.niq.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.niq.java.entity.Shopper;

@Repository
public interface ProductRepository extends JpaRepository<Shopper, String> {
    
    @Query(nativeQuery = true, value = "SELECT p.product_id, p.relevancy_score, pm.brand, pm.category "
            + "from product p inner join product_metadata pm on p.product_id=pm.product_id "
            + "where p.sp_fk= :shopperId and "
            + "(:category is null or pm.category = :category) and"
            + "(:brand is null or pm.brand = :brand)")
    List<Object[]> getProductsWithFilters(String shopperId, String category, String brand);
}


