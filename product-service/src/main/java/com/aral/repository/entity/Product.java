package com.aral.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@MappedSuperclass
public abstract class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String productName;
    String productDescription;
    Double productPrice;
    Integer stockQuantity;
    Long createdate;
    Long updatedate;
    boolean isactive;

}
