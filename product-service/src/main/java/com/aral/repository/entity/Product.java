package com.aral.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tblproduct")
@SuperBuilder
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "product_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long productId;
    String productName;
    String productDescription;
    Double productPrice;
    Integer productQuantity;
    Long createdate;
    Long updatedate;
    boolean isactive;

}
