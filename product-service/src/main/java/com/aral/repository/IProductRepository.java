package com.aral.repository;

import com.aral.repository.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findOptionalByProductId(Long id);
}
