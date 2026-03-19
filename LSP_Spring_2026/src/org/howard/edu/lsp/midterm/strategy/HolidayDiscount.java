package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for holiday customers.
 *
 * Author: Pradeep Lamichhane
 */
public class HolidayDiscount implements DiscountStrategy {

    /**
     * Applies a 15% discount for holiday customers.
     *
     * @param price the original purchase price
     * @return the discounted price
     */
    public double calculatePrice(double price) {
        return price * 0.85;
    }
}