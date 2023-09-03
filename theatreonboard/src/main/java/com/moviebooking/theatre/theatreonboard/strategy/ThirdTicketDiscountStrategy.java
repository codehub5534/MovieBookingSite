package com.moviebooking.theatre.theatreonboard.strategy;

import org.springframework.stereotype.Component;

@Component
public class ThirdTicketDiscountStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(int numOfTickets, double basePrice) {
        int discountedTickets = numOfTickets - numOfTickets / 3;
        return discountedTickets * basePrice;
    }
}
