package com.moviebooking.theatre.theatreonboard.strategy;

import org.springframework.stereotype.Component;

@Component
public class AfternoonShowDiscountStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(int numOfTickets, double basePrice) {
        return numOfTickets * basePrice * 0.8;
    }
}
