# REFLECTION

## Object-Oriented Redesign of My ETL Pipeline (A2 → A3)

---

## 1) What Changed from A2 to A3 (Design & Structure)

### Assignment 2 (A2): Monolithic Design

In Assignment 2, the entire ETL pipeline was implemented inside a single class (`ETLPipeline`). The `main` method handled:

* Reading the CSV file
* Parsing rows
* Applying transformation rules
* Writing output
* Tracking row counts
* Printing the summary

Although the program worked correctly, all responsibilities were tightly coupled inside one class. This made the code harder to extend, maintain, or test individual components independently.

---

### Assignment 3 (A3): Object-Oriented Decomposition

In Assignment 3, I refactored the program into multiple focused classes and interfaces:

### Data Model

* `Product` (immutable value object)
* `PriceRange` enum

### Extraction

* `ProductReader` interface
* `CsvProductReader` implementation
* `ReadResult` class to preserve row counts exactly as in A2

### Transformation

* `ProductTransformer` interface
* `DefaultProductTransformer` implementation

### Load

* `ProductWriter` interface
* `CsvProductWriter` implementation

### Orchestration

* `ETLPipelineMain` coordinates Extract → Transform → Load and prints the same summary as A2.

---

### Before vs After Comparison

| Concern       | A2 (Monolithic)          | A3 (Object-Oriented)                               |
| ------------- | ------------------------ | -------------------------------------------------- |
| Data Model    | Inner static class       | Immutable `Product` + `PriceRange` enum            |
| Extract       | `BufferedReader` in main | `ProductReader` → `CsvProductReader`               |
| Transform     | Inline logic in main     | `ProductTransformer` → `DefaultProductTransformer` |
| Load          | Inline write logic       | `ProductWriter` → `CsvProductWriter`               |
| Row Counts    | Variables inside main    | Encapsulated in `ReadResult`                       |
| Composition   | Hard-wired inside main   | `ETLPipelineMain` composes interfaces              |
| Extensibility | Modify monolith          | Add new components without changing others         |
| Testability   | Whole program only       | Components testable independently                  |

This redesign reduces coupling, improves cohesion, and makes the code easier to maintain.

---

## 2) How Assignment 3 is More Object-Oriented

### Objects & Classes

Real-world entities are modeled clearly:

* `Product` represents a product record.
* `CsvProductReader`, `DefaultProductTransformer`, and `CsvProductWriter` each represent a single responsibility.

### Encapsulation

* `Product` has private final fields and public getters.
* Parsing logic is encapsulated inside `CsvProductReader`.
* Transformation logic is encapsulated inside `DefaultProductTransformer`.
* Row statistics are encapsulated inside `ReadResult`.

### Abstraction

Interfaces define roles:

* `ProductReader`
* `ProductTransformer`
* `ProductWriter`

`ETLPipelineMain` depends on abstractions rather than concrete implementations.

### Polymorphism

Example usage:

```java
ProductReader reader = new CsvProductReader(in);
ProductTransformer transformer = new DefaultProductTransformer();
ProductWriter writer = new CsvProductWriter(out);
```

This allows different implementations to be substituted without changing the pipeline logic.

### Single Responsibility Principle (SRP)

Each class has one reason to change:

* Reader → parsing rules
* Transformer → business rules
* Writer → output formatting
* Main → orchestration

---

## 3) What Stayed the Same (To Preserve Correctness)

Assignment 3 preserves all observable behavior from Assignment 2:

### Inputs & Outputs

* Reads `data/products.csv`
* Writes `data/transformed_products.csv`
* Uses the same relative paths

### Transformation Rules (Identical to A2)

1. Uppercase product name
2. If category is exactly `"Electronics"` (case-sensitive):

   * Apply 10% discount
   * Round using `RoundingMode.HALF_UP` to 2 decimals
3. If discounted price > 500.00 and original category was `"Electronics"`:

   * Recategorize to `"Premium Electronics"`
4. Compute `PriceRange`:

   * ≤ 10.00 → Low
   * ≤ 100.00 → Medium
   * ≤ 500.00 → High
   * > 500.00 → Premium

### Row Counting Logic

The following behavior was preserved exactly:

* `rowsRead` increments for every non-header line encountered (including blank or malformed rows).
* `rowsSkipped` increments for invalid rows.
* `rowsTransformed` increments only when a row is successfully written.

I introduced the `ReadResult` class to preserve row statistics exactly as in A2.

### Output Formatting

* Prices always display exactly two decimal places.
* Header line remains identical.
* CSV format remains unchanged.

---

## 4) Why Certain Design Choices Were Made

### Interfaces for I/O and Transformation

Using interfaces decouples the orchestration from concrete implementations. In the future, it would be easy to add:

* `JsonProductReader`
* `DatabaseProductReader`
* `XmlProductWriter`

without modifying transformation logic.

### Immutable Product

Using `final` fields in `Product` prevents accidental modification and improves reliability.

### BigDecimal with HALF_UP

Currency calculations use `BigDecimal` with `RoundingMode.HALF_UP`, exactly as in A2, ensuring identical numeric results.

### Enum for PriceRange

Using an enum:

* Prevents typos
* Restricts valid values
* Improves readability

---

## 5) Testing Strategy to Prove A3 == A2

I validated Assignment 3 against Assignment 2 using identical input files.

### Test Cases

**1. Normal Input**

* Verified uppercase conversion
* Verified Electronics discount
* Verified Premium Electronics classification
* Verified correct price range assignment

**2. Mixed Delimiters**

* Confirmed support for commas, semicolons, and TABs

**3. Dollar Sign in Price**

* Verified `$9.99` parses correctly

**4. Malformed Rows**

* Missing columns → skipped
* Non-numeric ID → skipped
* Non-numeric price → skipped
* Summary counts match A2

**5. Empty Input File**

* Output contains only header
* Summary shows 0 rows read, transformed, skipped

**6. Missing Input File**

* Program prints:

  ```
  ERROR: Input file not found at path: data/products.csv
  ```
* Program exits cleanly

**7. Boundary Price Values**

* Tested 10.00, 100.00, 500.00
* Verified correct classification into Low, Medium, High, or Premium

---

## Conclusion

Assignment 3 significantly improved the structure and maintainability of the ETL pipeline without changing its functionality.

By applying object-oriented principles such as encapsulation, abstraction, and polymorphism, I created a cleaner and more extensible design while preserving identical observable behavior from Assignment 2.

---

