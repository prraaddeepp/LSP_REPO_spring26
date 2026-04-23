package org.howard.edu.lsp.finalexam.question2;

/**
 * Concrete report class for student information.
 */
public class StudentReport extends Report {
    private String studentName;
    private double gpa;

    /**
     * Loads student report data.
     */
    @Override
    protected void loadData() {
        studentName = "John Doe";
        gpa = 3.8;
    }

    /**
     * Formats the student report header.
     *
     * @return student report header
     */
    @Override
    protected String formatHeader() {
        return "Student Report";
    }

    /**
     * Formats the student report body.
     *
     * @return student report body
     */
    @Override
    protected String formatBody() {
        return "Student Name: " + studentName + "\nGPA: " + gpa;
    }

    /**
     * Formats the student report footer.
     *
     * @return student report footer
     */
    @Override
    protected String formatFooter() {
        return "End of Student Report";
    }
}