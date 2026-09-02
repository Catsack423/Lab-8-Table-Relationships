package com.lab.lab8.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lab.lab8.Entitys.Product;
import com.lab.lab8.Entitys.Review;
import com.lab.lab8.Repository.ProductRepository;
import com.lab.lab8.exception.ProductNotFoundException;
import com.lab.lab8.strategy.DiscountContext;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void save(Product product) {
        if (product.getDetail() != null && product.getDetail().getId() == null) {
            product.getDetail().setProduct(product);
        }
        setProductRefOnReviews(product);
        productRepository.save(product);
    }

    public void update(Long id, Product updatedProduct) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        existing.setName(updatedProduct.getName());
        existing.setCategory(updatedProduct.getCategory());
        existing.setBrand(updatedProduct.getBrand());
        existing.setStock(updatedProduct.getStock());
        existing.setPrice(updatedProduct.getPrice());
        existing.setDiscountType(updatedProduct.getDiscountType());

        // Update 1:1 ProductDetail
        if (updatedProduct.getDetail() != null) {
            updatedProduct.getDetail().setProduct(existing);
            existing.setDetail(updatedProduct.getDetail());
        }

        setProductRefOnReviews(existing);
        productRepository.save(existing);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public double getDiscountedPrice(Product product) {
        return DiscountContext.calculateDiscountedPrice(product.getPrice(), product.getDiscountType());
    }

    private void setProductRefOnReviews(Product product) {
        if (product.getReviews() != null) {
            for (Review review : product.getReviews()) {
                review.setProduct(product);
            }
        }
    }
}