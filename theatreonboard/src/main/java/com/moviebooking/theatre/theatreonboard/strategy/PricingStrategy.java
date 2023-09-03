package com.moviebooking.theatre.theatreonboard.strategy;

public interface PricingStrategy {
    double calculatePrice(int numOfTickets, double basePrice);
}

