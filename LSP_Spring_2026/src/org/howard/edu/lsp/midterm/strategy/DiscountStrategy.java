package org.howard.edu.lsp.midterm.strategy;

/**
 * Defines a strategy for calculating a discounted price.
 *
 * Author: Pradeep Lamichhane
 */
public interface DiscountStrategy {

    /**
     * Calculates the final price after applying a discount rule.
     *
     * @param price the original purchase price
     * @return the final discounted price
     */
    double calculatePrice(double price);
}