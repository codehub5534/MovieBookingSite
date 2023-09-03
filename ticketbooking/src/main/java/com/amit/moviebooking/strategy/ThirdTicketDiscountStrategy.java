package com.amit.moviebooking.strategy;

public class ThirdTicketDiscountStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(int numOfTickets, double basePrice) {
        int discountedTickets = numOfTickets - numOfTickets / 3;
        return discountedTickets * basePrice;
    }
}
