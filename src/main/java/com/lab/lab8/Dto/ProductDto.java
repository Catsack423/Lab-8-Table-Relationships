package com.lab.lab8.Dto;

import java.util.ArrayList;
import java.util.List;

import com.lab.lab8.Entitys.Product;
import com.lab.lab8.Entitys.ProductDetail;
import com.lab.lab8.Entitys.Review;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductDto {
    private Long id;

    @NotBlank(message = "ชื่อสินค้าห้ามว่าง")
    @Size(max = 100, message = "ชื่อสินค้ายาวเกิน 100 ตัวอักษร")
    private String name;

    @NotBlank(message = "หมวดหมู่ห้ามว่าง")
    private String category;

    @NotBlank(message = "แบรนด์ห้ามว่าง")
    private String brand;

    @NotNull(message = "จำนวนสต็อกห้ามว่าง")
    @Min(value = 0, message = "สต็อกต้องไม่ติดลบ")
    private Integer stock;

    @NotNull(message = "ราคาห้ามว่าง")
    @DecimalMin(value = "0.0", inclusive = true, message = "ราคาต้องไม่ติดลบ")
    private Double price;

    private String discountType;

    private ProductDetail detail = new ProductDetail();

    private List<Review> reviews = new ArrayList<>();

    public ProductDto() {
    }

    public static ProductDto fromEntity(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setCategory(product.getCategory());
        dto.setBrand(product.getBrand());
        dto.setStock(product.getStock());
        dto.setPrice(product.getPrice());
        dto.setDiscountType(product.getDiscountType());
        if (product.getDetail() != null) {
            dto.setDetail(product.getDetail());
        }
        if (product.getReviews() != null) {
            dto.setReviews(product.getReviews());
        }
        return dto;
    }

    public Product toEntity() {
        Product product = new Product();
        product.setId(this.id);
        product.setName(this.name);
        product.setCategory(this.category);
        product.setBrand(this.brand);
        product.setStock(this.stock);
        product.setPrice(this.price);
        product.setDiscountType(this.discountType);
        if (this.detail != null) {
            product.setDetail(this.detail);
        }
        if (this.reviews != null) {
            product.setReviews(this.reviews);
        }
        return product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public ProductDetail getDetail() {
        return detail;
    }

    public void setDetail(ProductDetail detail) {
        this.detail = detail;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
