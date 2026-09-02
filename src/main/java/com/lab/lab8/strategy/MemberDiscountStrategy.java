package com.lab.lab8.strategy;

public class MemberDiscountStrategy implements DiscountStrategy {
    @Override
    public double applyDiscount(double price) {
        return price * 0.9;
    }

    @Override
    public String getLabel() {
        return "MEMBER";
    }
}