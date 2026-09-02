package com.lab.lab8.strategy;

import java.util.Map;

public class DiscountContext {
    private static final Map<String, DiscountStrategy> STRATEGIES = Map.of(
        "NONE", new NoDiscountStrategy(),
        "MEMBER", new MemberDiscountStrategy(),
        "SEASONAL", new SeasonalSaleStrategy()
    );

    public static double calculateDiscountedPrice(double price, String discountType) {
        DiscountStrategy strategy = STRATEGIES.getOrDefault(discountType, STRATEGIES.get("NONE"));
        return strategy.applyDiscount(price);
    }
}