package com.example.api.beans;

import com.google.common.base.Preconditions;

import java.time.Instant;

public class TaskBuilder {
    private final Task task = new Task();

    public static TaskBuilder create() {
        return new TaskBuilder();
    }

    public TaskBuilder withTaskId(Long taskId) {
        task.taskId = taskId;
        return this;
    }

    public TaskBuilder withStatusId(TaskStatus statusId){
        task.statusId = statusId;
        return this;
    }

    public TaskBuilder withCreationDate(Instant creationDate){
        task.creationDate = creationDate;
        return this;
    }

    public TaskBuilder withTitle(String title) {
        task.title = title;
        return this;
    }

    public TaskBuilder withExpirationDate(Instant expirationDate) {
        task.expirationDate = expirationDate;
        return this;
    }

    public Task build() {
        Preconditions.checkArgument(task.taskId != null);
        Preconditions.checkArgument(task.statusId!=null);
        Preconditions.checkArgument(task.creationDate!=null);
        Preconditions.checkArgument(task.title != null);
        Preconditions.checkArgument(task.expirationDate != null);
        return task;
    }
}
