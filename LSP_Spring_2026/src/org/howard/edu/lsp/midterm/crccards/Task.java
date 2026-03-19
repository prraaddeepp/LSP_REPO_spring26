package org.howard.edu.lsp.midterm.crccards;

/**
 * Represents a task in the task management system.
 *
 * Author: Pradeep Lamichhane
 */
public class Task {
    private String taskId;
    private String description;
    private String status;

    /**
     * Constructs a Task with the given ID and description.
     * The default status is OPEN.
     *
     * @param taskId the unique ID of the task
     * @param description the description of the task
     */
    public Task(String taskId, String description) {
        this.taskId = taskId;
        this.description = description;
        this.status = "OPEN";
    }

    /**
     * Returns the task ID.
     *
     * @return the task ID
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * Returns the task description.
     *
     * @return the task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the task status.
     *
     * @return the task status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Updates the task status.
     * Valid values are OPEN, IN_PROGRESS, and COMPLETE.
     * Any invalid value sets the status to UNKNOWN.
     *
     * @param status the new task status
     */
    public void setStatus(String status) {
        if (status.equals("OPEN") || status.equals("IN_PROGRESS") || status.equals("COMPLETE")) {
            this.status = status;
        } else {
            this.status = "UNKNOWN";
        }
    }

    /**
     * Returns the task in the format:
     * taskId description [status]
     *
     * @return the formatted task string
     */
    @Override
    public String toString() {
        return taskId + " " + description + " [" + status + "]";
    }
}