package com.BerdanAkbulut.saleproductservice.repository;

import com.BerdanAkbulut.saleproductservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
