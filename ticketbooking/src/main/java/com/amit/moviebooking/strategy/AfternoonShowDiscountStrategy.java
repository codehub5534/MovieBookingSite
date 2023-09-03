package com.amit.moviebooking.strategy;

public class AfternoonShowDiscountStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(int numOfTickets, double basePrice) {
        return numOfTickets * basePrice * 0.8; // 20% discount
    }
}
