package com.aral.repository.entity;

import com.aral.utility.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
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
