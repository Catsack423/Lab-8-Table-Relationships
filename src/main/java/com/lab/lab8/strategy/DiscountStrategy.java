package com.lab.lab8.strategy;

public interface DiscountStrategy {
    double applyDiscount(double price);
    String getLabel();
}