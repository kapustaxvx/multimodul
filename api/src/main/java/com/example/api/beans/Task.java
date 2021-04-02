package com.example.api.beans;

import java.util.Objects;


public class Task {

    Long taskId;
    String title;
    String author;

    Task() {
    }

    public Long getTaskId() {
        return taskId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return Objects.equals(taskId, task.taskId) && Objects.equals(title, task.title) && Objects.equals(author, task.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, title, author);
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
