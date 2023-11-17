package com.aral.repository.entity;

import com.aral.utility.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Entity
@Table(name = "tblsales")
public class Sales extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long saleId;
    Long productId;
    Integer saleQuantity;
    Double price;


//    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
//    List<SaleItem> saleItems;

}
