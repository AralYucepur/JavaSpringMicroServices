package com.aral.repository;

import com.aral.repository.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISales extends JpaRepository<Sales, Long> {

}
