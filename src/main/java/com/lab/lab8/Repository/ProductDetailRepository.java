package com.lab.lab8.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lab.lab8.Entitys.ProductDetail;

public interface ProductDetailRepository extends JpaRepository<ProductDetail,Long> {
    
}
