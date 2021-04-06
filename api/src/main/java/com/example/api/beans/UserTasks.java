package com.example.api.beans;

import java.util.List;
import java.util.Objects;

public class UserTasks {

    Long userId;
    List<Task> tasksOfUser;

    public Long getUserId() {
        return userId;
    }

    public List<Task> getTasksOfUser() {
        return tasksOfUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTasks userTasks = (UserTasks) o;
        return Objects.equals(userId, userTasks.userId) &&
                Objects.equals(tasksOfUser, userTasks.tasksOfUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, tasksOfUser);
    }

    @Override
    public String toString() {
        return "UserTasks{" +
                "userId=" + userId +
                ", tasksOfUser=" + tasksOfUser +
                '}';
    }
}
