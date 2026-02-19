package org.howard.edu.lsp.assignment3;

import java.io.IOException;
import java.util.List;

/**
 * Defines a contract for writing transformed product data
 * to an output destination.
 *
 * <p>Implementations may write to different targets such as
 * CSV files, databases, or external services.
 */
public interface ProductWriter {

  /**
   * Writes all transformed rows to the output destination.
   *
   * <p>Each row is represented as a {@code String[]} containing
   * the fields to be written in order.
   *
   * @param rows list of transformed product rows
   * @throws IOException if an I/O error occurs during writing
   */
  void writeAll(List<String[]> rows) throws IOException;
}
