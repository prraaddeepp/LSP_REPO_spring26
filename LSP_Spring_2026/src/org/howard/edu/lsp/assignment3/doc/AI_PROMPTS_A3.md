# AI_PROMPTS_A3.md

## How I Used an AI Assistant (Overview)

**Goal:**
Redesign my Assignment 2 ETL pipeline into a properly decomposed object-oriented architecture while preserving *identical observable behavior* (same inputs, outputs, transformations, rounding rules, error handling, and relative paths).

**My role:**
I determined the final architecture, implemented the refactoring, verified behavioral equivalence with Assignment 2, and ensured all edge cases (row counts, rounding precision, missing/empty input handling) matched exactly.

**Methodology:**
I used AI for architectural brainstorming, validation of transformation logic, and clarification of edge-case behavior. All suggested changes were reviewed, implemented manually, compiled, and tested against Assignment 2 output before acceptance.

Below are representative prompts and short excerpts reflecting substantive design decisions.

---

## Prompt 1 — Object-Oriented Decomposition While Preserving Behavioral Equivalence

**My prompt (excerpt):**

> “My Assignment 2 ETL implementation is a single Java class that reads `data/products.csv`, applies transformation rules, and writes `data/transformed_products.csv`. I must redesign it into multiple classes using interfaces while preserving identical behavior, including row counting and error handling. Propose an OO decomposition that maintains functional equivalence.”

**AI response (short excerpt):**

> “Separate responsibilities into a data model (`Product`), extraction component (`ProductReader`), transformation component (`ProductTransformer`), loading component (`ProductWriter`), and an orchestration class (`ETLPipelineMain`). Ensure the transformation order and rounding logic remain unchanged.”

**What I accepted and refined:**

* Adopted interface-based design for extraction, transformation, and loading.
* Implemented:

  * `Product` (immutable model)
  * `ProductReader` / `CsvProductReader`
  * `ProductTransformer` / `DefaultProductTransformer`
  * `ProductWriter` / `CsvProductWriter`
  * `ETLPipelineMain` for orchestration
* Preserved relative file paths and summary formatting exactly as in A2.
* Ensured separation of concerns without altering external behavior.

---

## Prompt 2 — Preserving Exact Row Counting Semantics from A2

**My prompt (excerpt):**

> “In Assignment 2, `rowsRead` increments for every non-header line encountered, including blank and malformed rows. In my initial A3 design, returning only valid `Product` objects caused row counts to differ. How can I preserve the exact A2 row counting semantics while maintaining clean OO design?”

**AI response (short excerpt):**

> “Introduce a result wrapper (e.g., `ReadResult`) that includes both the parsed product list and row statistics. Perform row counting inside the reader while parsing.”

**What I implemented:**

* Created a `ReadResult` class encapsulating:

  * `List<Product>`
  * `rowsRead`
  * `rowsSkipped`
* Modified `ProductReader.readAll()` to return `ReadResult`.
* Ensured:

  * `rowsRead` increments for every non-header line encountered.
  * `rowsSkipped` increments for blank or malformed rows.
* Verified that A3 summary output matches A2 exactly.

This change preserved correctness while maintaining separation of concerns.

---

## Prompt 3 — Ensuring Transformation Order and Rounding Behavior Match A2 Exactly

**My prompt (excerpt):**

> “I need to ensure my `DefaultProductTransformer` matches A2 exactly: uppercase name, apply 10% discount only when category is exactly `\"Electronics\"` (case-sensitive), round HALF_UP to two decimals after discount decision, and recategorize to `\"Premium Electronics\"` only if the final rounded price > 500.00. Confirm correct order and comparisons.”

**AI response (short excerpt):**

> “Apply discount first (if category equals `\"Electronics\"`), then round using `setScale(2, HALF_UP)`. Use a strict `>` comparison for the premium rule. Keep case-sensitive equality.”

**What I implemented and verified:**

* Used `"Electronics".equals(category)` (not case-insensitive comparison).
* Applied rounding after discount calculation.
* Used strict `>` 500.00 comparison.
* Ensured `PriceRange` thresholds remain:

  * ≤ 10 → Low
  * ≤ 100 → Medium
  * ≤ 500 → High
  * > 500 → Premium

Behavioral output was validated against A2.

---

## Prompt 4 — Guaranteeing Output Precision (Two Decimal Places)

**My prompt (excerpt):**

> “Assignment 2 always produced prices with exactly two decimal places (e.g., 150.00). How do I ensure Assignment 3 maintains identical formatting in the CSV output?”

**AI response (short excerpt):**

> “Ensure values are rounded to scale 2 using `RoundingMode.HALF_UP`, and format output explicitly when writing rows (e.g., `setScale(2).toPlainString()`).”

**What I implemented:**

* Maintained rounding inside the transformer.
* Enforced two-decimal formatting during output writing.
* Verified that output file contains values like:

  * 150.00
  * 1080.00
  * 500.40

This ensured byte-level equivalence with A2 output.

---

## Prompt 5 — Validating Missing and Empty Input Behavior

**My prompt (excerpt):**

> “Assignment 3 requires verification of missing input and empty input file cases. How can I ensure these behave exactly as in A2?”

**AI response (short excerpt):**

> “Add a missing-file check at the start of main and exit cleanly. For empty input files, write only the header and print a zero-count summary.”

**What I implemented:**

* Added explicit missing-file check in `ETLPipelineMain`.
* Ensured:

  * Missing file → prints identical error message and exits.
  * Empty file (0 bytes) → header-only output + zero-count summary.
* Confirmed behavior matches A2 exactly.

---

## Verification Strategy

To confirm behavioral equivalence between A2 and A3:

* Ran both programs using the same `products.csv`.
* Compared `transformed_products.csv` outputs directly.
* Verified identical:

  * Transformed values
  * Rounding results
  * PriceRange classifications
  * Summary counts (`rowsRead`, `rowsTransformed`, `rowsSkipped`)
* Tested edge cases:

  * Blank rows
  * Malformed rows
  * Boundary price values (10.00, 100.00, 500.00)
  * Missing input file
  * Empty input file

---

## Reflection on AI Usage

AI was used as a design and verification assistant, not as an automated code generator. I made all architectural decisions, manually implemented the refactoring, and validated correctness through systematic testing.

All AI suggestions were critically evaluated and adapted. The final solution reflects my own understanding of object-oriented decomposition and program correctness while preserving strict behavioral equivalence with Assignment 2.

---
