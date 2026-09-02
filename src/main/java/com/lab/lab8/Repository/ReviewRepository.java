package com.lab.lab8.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lab.lab8.Entitys.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    
}
