package com.amit.moviebooking.strategy;

public class NoDiscountStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(int numOfTickets, double basePrice) {
        return numOfTickets * basePrice;
    }
}
