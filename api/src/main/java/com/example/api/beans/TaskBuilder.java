package com.example.api.beans;

import com.google.common.base.Preconditions;

public class TaskBuilder {
    private final Task task = new Task();

    public static TaskBuilder create() {
        return new TaskBuilder();
    }

    public TaskBuilder withTaskId(Long taskId) {
        task.taskId = taskId;
        return this;
    }

    public TaskBuilder withTitle(String title) {
        task.title = title;
        return this;
    }

    public TaskBuilder withAuthor(String author) {
        task.author = author;
        return this;
    }

    public Task build() {
        Preconditions.checkArgument(task.taskId != null);
        Preconditions.checkArgument(task.title != null);
        Preconditions.checkArgument(task.author != null);
        return task;
    }
}
