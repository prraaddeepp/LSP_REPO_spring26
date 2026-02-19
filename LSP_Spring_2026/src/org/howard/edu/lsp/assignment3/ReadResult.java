package org.howard.edu.lsp.assignment3;

import java.util.List;

/**
 * Encapsulates the result of reading product data from a source.
 *
 * <p>This class stores:
 * <ul>
 *   <li>The list of successfully parsed {@link Product} objects</li>
 *   <li>The total number of non-header rows encountered</li>
 *   <li>The number of rows skipped due to formatting or parsing errors</li>
 * </ul>
 *
 * <p>This design preserves Assignment 2’s row counting semantics
 * while maintaining separation of concerns in Assignment 3.
 */
public class ReadResult {

  private final List<Product> products;
  private final int rowsRead;
  private final int rowsSkipped;

  /**
   * Constructs a ReadResult containing parsed products and row statistics.
   *
   * @param products    list of successfully parsed products
   * @param rowsRead    number of non-header rows encountered
   * @param rowsSkipped number of rows skipped due to errors
   */
  public ReadResult(List<Product> products, int rowsRead, int rowsSkipped) {
    this.products = products;
    this.rowsRead = rowsRead;
    this.rowsSkipped = rowsSkipped;
  }

  /**
   * Returns the list of successfully parsed products.
   *
   * @return list of {@link Product} objects
   */
  public List<Product> getProducts() {
    return products;
  }

  /**
   * Returns the number of non-header rows encountered.
   *
   * @return number of rows read
   */
  public int getRowsRead() {
    return rowsRead;
  }

  /**
   * Returns the number of rows skipped due to parsing or formatting errors.
   *
   * @return number of skipped rows
   */
  public int getRowsSkipped() {
    return rowsSkipped;
  }
}
