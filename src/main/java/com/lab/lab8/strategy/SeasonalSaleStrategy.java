package com.lab.lab8.strategy;

public class SeasonalSaleStrategy implements DiscountStrategy {
    @Override
    public double applyDiscount(double price) {
        return price * 0.8;
    }

    @Override
    public String getLabel() {
        return "SEASONAL";
    }
}