package com.example.api.beans;

import java.time.Instant;
import java.util.Objects;


public class Task {

    Long taskId;
    TaskStatus statusId;
    Instant creationDate;
    String title;
    Instant expirationDate;

    Task() {
    }

    public Long getTaskId() {
        return taskId;
    }

    public TaskStatus getStatusId() {
        return statusId;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public String getTitle() {
        return title;
    }

    public Instant getExpirationDate() {
        return expirationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(taskId, task.taskId) &&
                statusId == task.statusId &&
                Objects.equals(creationDate, task.creationDate) &&
                Objects.equals(title, task.title) &&
                Objects.equals(expirationDate, task.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, statusId, creationDate, title, expirationDate);
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", statusId=" + statusId +
                ", creationDate=" + creationDate +
                ", title='" + title + '\'' +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
