package com.online.ecommerce.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.online.ecommerce.application.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>{

}
