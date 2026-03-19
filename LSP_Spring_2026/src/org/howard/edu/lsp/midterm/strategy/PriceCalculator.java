package org.howard.edu.lsp.midterm.strategy;

/**
 * Uses a DiscountStrategy to calculate final prices.
 *
 * Author: Pradeep Lamichhane
 */
public class PriceCalculator {
    private DiscountStrategy strategy;

    /**
     * Constructs a PriceCalculator with the given pricing strategy.
     *
     * @param strategy the discount strategy to use
     */
    public PriceCalculator(DiscountStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Calculates the final price using the current strategy.
     *
     * @param price the original purchase price
     * @return the final price after discount
     */
    public double calculatePrice(double price) {
        return strategy.calculatePrice(price);
    }
}