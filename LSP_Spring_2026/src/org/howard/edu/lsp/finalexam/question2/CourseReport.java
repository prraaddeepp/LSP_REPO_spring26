package org.howard.edu.lsp.finalexam.question2;

/**
 * Concrete report class for course information.
 */
public class CourseReport extends Report {
    private String courseName;
    private int enrollment;

    /**
     * Loads course report data.
     */
    @Override
    protected void loadData() {
        courseName = "CSCI 363";
        enrollment = 45;
    }

    /**
     * Formats the course report header.
     *
     * @return course report header
     */
    @Override
    protected String formatHeader() {
        return "Course Report";
    }

    /**
     * Formats the course report body.
     *
     * @return course report body
     */
    @Override
    protected String formatBody() {
        return "Course: " + courseName + "\nEnrollment: " + enrollment;
    }

    /**
     * Formats the course report footer.
     *
     * @return course report footer
     */
    @Override
    protected String formatFooter() {
        return "End of Course Report";
    }
}