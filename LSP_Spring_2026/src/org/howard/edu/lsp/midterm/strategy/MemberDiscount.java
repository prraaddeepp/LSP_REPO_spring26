package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for member customers.
 *
 * Author: Pradeep Lamichhane
 */
public class MemberDiscount implements DiscountStrategy {

    /**
     * Applies a 10% discount for member customers.
     *
     * @param price the original purchase price
     * @return the discounted price
     */
    public double calculatePrice(double price) {
        return price * 0.90;
    }
}