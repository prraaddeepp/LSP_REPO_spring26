package org.howard.edu.lsp.finalexam.question2;

/**
 * Abstract base class for reports using the Template Method pattern.
 * The report generation workflow is fixed, while subclasses provide
 * report-specific data and formatting.
 */
public abstract class Report {
    /**
     * Loads the data needed for the report.
     */
    protected abstract void loadData();

    /**
     * Formats the report header.
     *
     * @return the formatted header content
     */
    protected abstract String formatHeader();

    /**
     * Formats the report body.
     *
     * @return the formatted body content
     */
    protected abstract String formatBody();

    /**
     * Formats the report footer.
     *
     * @return the formatted footer content
     */
    protected abstract String formatFooter();

    /**
     * Template method that defines the fixed report generation workflow.
     */
    public final void generateReport() {
        loadData();

        System.out.println("=== HEADER ===");
        System.out.println(formatHeader());

        System.out.println("=== BODY ===");
        System.out.println(formatBody());

        System.out.println("=== FOOTER ===");
        System.out.println(formatFooter());
    }
}