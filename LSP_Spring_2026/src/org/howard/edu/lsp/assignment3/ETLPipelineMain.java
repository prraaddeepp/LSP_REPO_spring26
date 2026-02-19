package org.howard.edu.lsp.assignment3;

import java.math.RoundingMode;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Entry point for Assignment 3's ETL pipeline.
 *
 * <p>This class orchestrates the end-to-end ETL flow using the object-oriented
 * components defined in the {@code org.howard.edu.lsp.assignment3} package:
 * <ul>
 *   <li>Extract: {@link ProductReader} (implemented by {@link CsvProductReader})</li>
 *   <li>Transform: {@link ProductTransformer} (implemented by {@link DefaultProductTransformer})</li>
 *   <li>Load: {@link ProductWriter} (implemented by {@link CsvProductWriter})</li>
 * </ul>
 *
 * <p>The program preserves Assignment 2's observable behavior:
 * same relative file paths, same transformation logic, same output format, and
 * the same run summary counters (rows read/transformed/skipped).
 */
public class ETLPipelineMain {

  /**
   * Runs the ETL pipeline using relative paths:
   * {@code data/products.csv} as input and {@code data/transformed_products.csv} as output.
   *
   * <p>Reads products, applies the transformation rules, writes the transformed CSV,
   * and prints a run summary.
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    Path in = Path.of("data", "products.csv");
    Path out = Path.of("data", "transformed_products.csv");

    int rowsRead = 0;
    int rowsTransformed = 0;
    int rowsSkipped = 0;

    try {
      ProductReader reader = new CsvProductReader(in);
      ProductTransformer transformer = new DefaultProductTransformer();
      ProductWriter writer = new CsvProductWriter(out);

      ReadResult result = reader.readAll();
      rowsRead = result.getRowsRead();
      rowsSkipped = result.getRowsSkipped();

      List<String[]> rows = new ArrayList<>();
      for (Product p : result.getProducts()) {
        Product t = transformer.transform(p);

        // In A2, any product that successfully parsed is transformed and written.
        rowsTransformed++;

        rows.add(new String[] {
            String.valueOf(t.getProductId()),
            t.getName(),
            // Always output two decimal places (matches A2 formatting).
            t.getPrice().setScale(2, RoundingMode.HALF_UP).toPlainString(),
            t.getCategory(),
            transformer.priceRange(t.getPrice()).name()
        });
      }

      writer.writeAll(rows);

    } catch (Exception e) {
      // Keep error output simple and avoid stack traces (consistent with A2 style).
      System.out.println("ERROR: " + e.getMessage());
      return;
    }

    // Match A2's summary block formatting.
    System.out.println("Run Summary");
    System.out.println("-----------");
    System.out.println("Number of rows read (non-header): " + rowsRead);
    System.out.println("Number of rows transformed:       " + rowsTransformed);
    System.out.println("Number of rows skipped:           " + rowsSkipped);
    System.out.println("Output file path written:         " + out.toString());
  }
}
