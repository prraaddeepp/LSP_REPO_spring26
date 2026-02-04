//Name:Pradeep Lamichhane
//CSCI 363/540 - Assignment 2: ETL Pipeline With Relative Paths

package org.howard.edu.lsp.assignment2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ETLPipeline {

    // Simple record for one CSV row after parsing
    private static class Product {
        int productId;
        String name;
        BigDecimal price; // currency-safe math
        String category;

        Product(int id, String name, BigDecimal price, String category) {
            this.productId = id;
            this.name = name;
            this.price = price;
            this.category = category;
        }
    }

    public static void main(String[] args) {
        // Relative paths so the project runs the same on any machine
        Path inputPath = Paths.get("data", "products.csv");
        Path outputPath = Paths.get("data", "transformed_products.csv");

        int rowsRead = 0;         // non-header lines encountered (including bad ones + blanks)
        int rowsTransformed = 0;  // written to output
        int rowsSkipped = 0;      // skipped rows

        // Case C: Missing input file
        if (!Files.exists(inputPath) || !Files.isRegularFile(inputPath)) {
            System.out.println("ERROR: Input file not found at path: " + inputPath.toString());
            return; // clean exit, no stack trace
        }

        // Ensure output directory exists
        try {
            Files.createDirectories(outputPath.getParent());
        } catch (IOException e) {
            System.out.println("ERROR: Could not create output directory: " + e.getMessage());
            return;
        }

        // Extract + Transform + Load in one pass (less error-prone)
        try (BufferedReader br = Files.newBufferedReader(inputPath);
             BufferedWriter bw = Files.newBufferedWriter(outputPath)) {

            // Read header (must not be transformed)
            String header = br.readLine();

            // Always write output header row
            bw.write("ProductID,Name,Price,Category,PriceRange");
            bw.newLine();

            // If file is truly empty (no header line at all), output header only
            if (header == null) {
                printSummary(rowsRead, rowsTransformed, rowsSkipped, outputPath);
                return;
            }

            String line;
            while ((line = br.readLine()) != null) {
                // Every non-header line encountered counts as read (even blank/bad)
                rowsRead++;

                // Row skipping rule: blank row
                if (line.trim().isEmpty()) {
                    rowsSkipped++;
                    continue;
                }

                // Spec: delimiter is comma, must have exactly 4 fields
                String[] parts = line.split(",", -1);
                if (parts.length != 4) {
                    rowsSkipped++;
                    continue;
                }

                // Trim whitespace around each field before processing
                String productIdStr = parts[0].trim();
                String name = parts[1].trim();
                String priceStr = parts[2].trim();
                String category = parts[3].trim();

                // Parse ProductID
                int id;
                try {
                    id = Integer.parseInt(productIdStr);
                } catch (NumberFormatException e) {
                    rowsSkipped++;
                    continue;
                }

                // Parse Price
                BigDecimal price;
                try {
                    price = new BigDecimal(priceStr);
                } catch (NumberFormatException e) {
                    rowsSkipped++;
                    continue;
                }

                // Build product and transform
                Product p = new Product(id, name, price, category);
                Product t = transform(p);

                // PriceRange computed from FINAL rounded price
                String priceRange = computePriceRange(t.price);

                // Load: write output row (price always with exactly 2 decimals)
                bw.write(t.productId + ","
                        + t.name + ","
                        + formatTwoDecimals(t.price) + ","
                        + t.category + ","
                        + priceRange);
                bw.newLine();

                rowsTransformed++;
            }

        } catch (IOException e) {
            System.out.println("ERROR: I/O failure while processing files. " + e.getMessage());
            return;
        }

        // End of run summary
        printSummary(rowsRead, rowsTransformed, rowsSkipped, outputPath);
    }

    // Apply specified transforms IN EXACT ORDER:
    // 1) uppercase name
    // 2) 10% discount if category is exactly "Electronics"
    // 3) if final rounded price > 500.00 AND original category was "Electronics" -> Premium Electronics
    // NOTE: rounding is HALF_UP to 2 decimals and is applied after discount
    private static Product transform(Product p) {
        String originalCategory = p.category;

        // 1) uppercase
        String newName = (p.name == null) ? "" : p.name.toUpperCase();

        // 2) discount if exactly "Electronics" (case-sensitive to match spec + expected output)
        BigDecimal newPrice = p.price;
        if ("Electronics".equals(p.category)) {
            newPrice = newPrice.multiply(new BigDecimal("0.90"));
        }

        // Round HALF_UP to exactly 2 decimals
        newPrice = newPrice.setScale(2, RoundingMode.HALF_UP);

        // 3) premium electronics rule uses final rounded price + original category
        String newCategory = p.category;
        if ("Electronics".equals(originalCategory) && newPrice.compareTo(new BigDecimal("500.00")) > 0) {
            newCategory = "Premium Electronics";
        }

        return new Product(p.productId, newName, newPrice, newCategory);
    }

    // Price Range based on FINAL rounded price:
    // <= 10.00 -> Low
    // > 10.00 and <= 100.00 -> Medium
    // > 100.00 and <= 500.00 -> High
    // > 500.00 -> Premium
    private static String computePriceRange(BigDecimal finalPrice) {
        if (finalPrice.compareTo(new BigDecimal("10.00")) <= 0) return "Low";
        if (finalPrice.compareTo(new BigDecimal("100.00")) <= 0) return "Medium";
        if (finalPrice.compareTo(new BigDecimal("500.00")) <= 0) return "High";
        return "Premium";
    }

    private static String formatTwoDecimals(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_UP).toPlainString();
    }

    private static void printSummary(int rowsRead, int rowsTransformed, int rowsSkipped, Path outputPath) {
        System.out.println("Run Summary");
        System.out.println("-----------");
        System.out.println("Number of rows read (non-header): " + rowsRead);
        System.out.println("Number of rows transformed:       " + rowsTransformed);
        System.out.println("Number of rows skipped:           " + rowsSkipped);
        System.out.println("Output file path written:         " + outputPath.toString());
    }
}
