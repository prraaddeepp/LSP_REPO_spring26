package org.howard.edu.lsp.assignment3;

import java.io.IOException;

/**
 * Defines a contract for extracting {@link Product} records from a data source.
 *
 * <p>Implementations may read products from different sources such as
 * CSV files, databases, or APIs. The reader is responsible for parsing
 * raw input data and returning both the parsed products and row statistics.
 */
public interface ProductReader {

  /**
   * Reads all products from the underlying data source.
   *
   * <p>This method returns a {@link ReadResult} containing:
   * <ul>
   *   <li>The list of successfully parsed products</li>
   *   <li>The number of rows read (non-header)</li>
   *   <li>The number of rows skipped due to errors or formatting issues</li>
   * </ul>
   *
   * @return a {@link ReadResult} containing parsed products and row statistics
   * @throws IOException if an I/O error occurs while accessing the data source
   */
  ReadResult readAll() throws IOException;
}
