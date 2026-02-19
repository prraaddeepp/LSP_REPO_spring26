package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Default implementation of {@link ProductTransformer}.
 *
 * <p>This class applies the exact transformation rules defined in Assignment 2:
 * <ul>
 *   <li>Uppercase the product name.</li>
 *   <li>If category is exactly "Electronics", apply a 10% discount.</li>
 *   <li>Round all prices to two decimal places using {@code HALF_UP}.</li>
 *   <li>If the final rounded price is strictly greater than 500.00 and the
 *       original category was "Electronics", recategorize to
 *       "Premium Electronics".</li>
 * </ul>
 */
public class DefaultProductTransformer implements ProductTransformer {

  /**
   * Applies transformation rules to a single product.
   *
   * @param p the original product
   * @return a new transformed {@link Product} instance
   */
  @Override
  public Product transform(Product p) {
    if (p == null) {
      return null;
    }

    String originalCategory = p.getCategory();

    // 1) Uppercase name
    String newName = (p.getName() == null) ? "" : p.getName().toUpperCase();

    // 2) Apply discount if category is exactly "Electronics"
    BigDecimal price = p.getPrice();
    if ("Electronics".equals(originalCategory)) {
      price = price.multiply(BigDecimal.valueOf(0.9));
    }

    // Round to 2 decimals (HALF_UP) exactly as in A2
    price = price.setScale(2, RoundingMode.HALF_UP);

    // 3) Recategorize if premium condition is met
    String newCategory = originalCategory;
    if ("Electronics".equals(originalCategory)
        && price.compareTo(new BigDecimal("500.00")) > 0) {
      newCategory = "Premium Electronics";
    }

    return new Product(p.getProductId(), newName, price, newCategory);
  }

  /**
   * Determines the price range category based on the final rounded price.
   *
   * <ul>
   *   <li>≤ 10.00 → Low</li>
   *   <li>≤ 100.00 → Medium</li>
   *   <li>≤ 500.00 → High</li>
   *   <li>> 500.00 → Premium</li>
   * </ul>
   *
   * @param price the final rounded price
   * @return the corresponding {@link PriceRange}
   */
  @Override
  public PriceRange priceRange(BigDecimal price) {
    if (price.compareTo(new BigDecimal("10.00")) <= 0) {
      return PriceRange.Low;
    }
    if (price.compareTo(new BigDecimal("100.00")) <= 0) {
      return PriceRange.Medium;
    }
    if (price.compareTo(new BigDecimal("500.00")) <= 0) {
      return PriceRange.High;
    }
    return PriceRange.Premium;
  }
}
