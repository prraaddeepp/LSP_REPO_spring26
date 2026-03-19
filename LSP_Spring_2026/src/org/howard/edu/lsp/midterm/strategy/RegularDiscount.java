package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for regular customers.
 *
 * Author: Pradeep Lamichhane
 */
public class RegularDiscount implements DiscountStrategy {

    /**
     * Returns the original price for a regular customer.
     *
     * @param price the original purchase price
     * @return the unchanged price
     */
    public double calculatePrice(double price) {
        return price;
    }
}