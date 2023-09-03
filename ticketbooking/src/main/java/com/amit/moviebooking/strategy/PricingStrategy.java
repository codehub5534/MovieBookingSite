package com.amit.moviebooking.strategy;

public interface PricingStrategy {
    double calculatePrice(int numOfTickets, double basePrice);
}

