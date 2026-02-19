package org.howard.edu.lsp.assignment3;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Writes transformed product data to a CSV file.
 *
 * <p>This writer produces output identical in structure to Assignment 2:
 * it writes a fixed header line followed by transformed product rows.
 * The output directory is created if it does not already exist.
 */
public class CsvProductWriter implements ProductWriter {

  private final Path outputPath;

  /**
   * Constructs a writer that will write output to the given file path.
   *
   * @param outputPath path to the output CSV file (relative paths supported)
   */
  public CsvProductWriter(Path outputPath) {
    this.outputPath = outputPath;
  }

  /**
   * Writes all transformed rows to the output CSV file.
   *
   * <p>The file always begins with the header:
   * {@code ProductID,Name,Price,Category,PriceRange}.
   * Each row is written as a comma-separated line.
   *
   * @param rows list of string arrays representing transformed product rows
   * @throws IOException if an I/O error occurs while writing the file
   */
  @Override
  public void writeAll(List<String[]> rows) throws IOException {
    Files.createDirectories(outputPath.getParent());

    try (BufferedWriter bw = Files.newBufferedWriter(outputPath)) {
      bw.write("ProductID,Name,Price,Category,PriceRange");
      bw.newLine();

      for (String[] r : rows) {
        bw.write(String.join(",", r));
        bw.newLine();
      }
    }
  }
}
