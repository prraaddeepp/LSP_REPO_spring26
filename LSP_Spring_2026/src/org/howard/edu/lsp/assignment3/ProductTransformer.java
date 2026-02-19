package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;

/**
 * Defines a contract for transforming {@link Product} objects
 * and determining their price range classification.
 *
 * <p>Implementations encapsulate the business rules applied
 * during the transformation stage of the ETL pipeline.
 */
public interface ProductTransformer {

  /**
   * Applies transformation rules to a product.
   *
   * <p>This may include operations such as modifying the name,
   * adjusting the price, or changing the category based on
   * business logic.
   *
   * @param p the original product
   * @return a new transformed {@link Product}
   */
  Product transform(Product p);

  /**
   * Determines the price range category based on the product's
   * final rounded price.
   *
   * @param price the final rounded price of the product
   * @return the corresponding {@link PriceRange}
   */
  PriceRange priceRange(BigDecimal price);
}
