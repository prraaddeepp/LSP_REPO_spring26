package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for VIP customers.
 *
 * Author: Pradeep Lamichhane
 */
public class VipDiscount implements DiscountStrategy {

    /**
     * Applies a 20% discount for VIP customers.
     *
     * @param price the original purchase price
     * @return the discounted price
     */
    public double calculatePrice(double price) {
        return price * 0.80;
    }
}