package org.howard.edu.lsp.assignment3;

/**
 * Represents the classification of a product based on its final rounded price.
 *
 * <p>The categories correspond to the thresholds defined in Assignment 2:
 * <ul>
 *   <li>Low      → price ≤ 10.00</li>
 *   <li>Medium   → price ≤ 100.00</li>
 *   <li>High     → price ≤ 500.00</li>
 *   <li>Premium  → price > 500.00</li>
 * </ul>
 *
 * <p>This enum improves type safety and avoids string-based errors
 * when determining price ranges.
 */
public enum PriceRange {
    Low,
    Medium,
    High,
    Premium
}
