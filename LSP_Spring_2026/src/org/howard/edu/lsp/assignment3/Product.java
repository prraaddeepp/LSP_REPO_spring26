package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;

/**
 * Immutable data model representing a single product record.
 *
 * <p>This class encapsulates the core attributes of a product:
 * product ID, name, price, and category. Instances are immutable,
 * meaning their state cannot be changed after construction.
 *
 * <p>Immutability helps prevent accidental modification during
 * transformation stages of the ETL pipeline.
 */
public final class Product {

  private final int productId;
  private final String name;
  private final BigDecimal price;
  private final String category;

  /**
   * Constructs a Product instance.
   *
   * @param productId unique identifier for the product
   * @param name      product name
   * @param price     product price (represented using BigDecimal for precision)
   * @param category  product category
   */
  public Product(int productId, String name, BigDecimal price, String category) {
    this.productId = productId;
    this.name = name;
    this.price = price;
    this.category = category;
  }

  /**
   * Returns the product ID.
   *
   * @return product ID
   */
  public int getProductId() {
    return productId;
  }

  /**
   * Returns the product name.
   *
   * @return product name
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the product price.
   *
   * @return product price as BigDecimal
   */
  public BigDecimal getPrice() {
    return price;
  }

  /**
   * Returns the product category.
   *
   * @return product category
   */
  public String getCategory() {
    return category;
  }
}
