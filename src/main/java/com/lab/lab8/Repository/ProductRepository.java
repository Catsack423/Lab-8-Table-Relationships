package com.lab.lab8.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lab.lab8.Entitys.Product;
import com.lab.lab8.Entitys.Review;

public interface ProductRepository extends JpaRepository<Product,Long>{
    
}
