//package com.aral.repository.entity;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//@Builder
//public class SaleItem {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    Long saleId;
//    Long productId;
//    Integer quantity;
//    Double price;
//    @ManyToOne
//    @JoinColumn(name = "sale_id", nullable = false)
//    Sales sale;
//
//}
