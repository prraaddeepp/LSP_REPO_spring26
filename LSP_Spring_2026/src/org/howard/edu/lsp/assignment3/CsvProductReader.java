package org.howard.edu.lsp.assignment3;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads {@link Product} records from a CSV file using the same parsing and row-skipping
 * behavior as Assignment 2.
 *
 * <p>Behavioral notes (to match A2):
 * <ul>
 *   <li>If the input file is missing, prints an error message and returns an empty result.</li>
 *   <li>Reads and ignores the header line (first line).</li>
 *   <li>Counts every non-header line encountered as {@code rowsRead}, including blank/malformed lines.</li>
 *   <li>Skips blank lines, lines without exactly 4 comma-separated fields, and lines with non-numeric
 *       ProductID or Price.</li>
 * </ul>
 */
public class CsvProductReader implements ProductReader {
  private final Path inputPath;

  /**
   * Constructs a reader that will parse products from the given input path.
   *
   * @param inputPath path to the input CSV file (relative paths supported)
   */
  public CsvProductReader(Path inputPath) {
    this.inputPath = inputPath;
  }

  /**
   * Reads all products from the configured CSV file and returns the parsed products along with
   * row statistics needed to match Assignment 2's run summary.
   *
   * <p>This method does not transform the products; it only parses and validates rows.
   *
   * @return a {@link ReadResult} containing the list of successfully parsed products, the number of
   *         non-header lines encountered, and the number of skipped rows
   * @throws IOException if an I/O error occurs while opening or reading the file
   */
  @Override
  public ReadResult readAll() throws IOException {
    List<Product> out = new ArrayList<>();
    int rowsRead = 0;
    int rowsSkipped = 0;

    // Match A2 behavior + message
    if (!Files.exists(inputPath) || !Files.isRegularFile(inputPath)) {
      System.out.println("ERROR: Input file not found at path: " + inputPath.toString());
      return new ReadResult(List.of(), 0, 0);
    }

    try (BufferedReader br = Files.newBufferedReader(inputPath)) {
      String header = br.readLine(); // header
      if (header == null) {
        // Truly empty file: A2 writes header-only output later
        return new ReadResult(out, 0, 0);
      }

      String line;
      while ((line = br.readLine()) != null) {
        // EXACT A2: count every non-header line as read
        rowsRead++;

        if (line.trim().isEmpty()) {
          rowsSkipped++;
          continue;
        }

        // A2 delimiter: comma only
        String[] parts = line.split(",", -1);
        if (parts.length != 4) {
          rowsSkipped++;
          continue;
        }

        String productIdStr = parts[0].trim();
        String name = parts[1].trim();
        String priceStr = parts[2].trim();
        String category = parts[3].trim();

        try {
          int id = Integer.parseInt(productIdStr);
          BigDecimal price = new BigDecimal(priceStr);
          out.add(new Product(id, name, price, category));
        } catch (Exception e) {
          rowsSkipped++;
        }
      }
    }

    return new ReadResult(out, rowsRead, rowsSkipped);
  }
}
