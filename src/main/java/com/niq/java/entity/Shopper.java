package com.niq.java.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "shopper")
public class Shopper {

    @Id
    @Column(name = "shopper_id")
    private String shopperId;
    
    @OneToMany(targetEntity = Product.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "sp_fk", referencedColumnName = "shopper_id")
    private List<Product> shelf;
}
