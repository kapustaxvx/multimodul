package com.example.api.beans;

import com.google.common.base.Preconditions;

import java.util.List;

public class UserTasksBuilder {

    private final UserTasks userTasks = new UserTasks();

    public static UserTasksBuilder create() {
        return new UserTasksBuilder();
    }

    UserTasksBuilder withUserId(Long userId) {
        userTasks.userId = userId;
        return this;
    }

    UserTasksBuilder withTasks(List<Task> taskList){
        userTasks.tasksOfUser = taskList;
        return this;
    }

    public UserTasks build(){
        Preconditions.checkArgument(userTasks.userId!=null);
        Preconditions.checkArgument(userTasks.tasksOfUser!=null);
        return userTasks;
    }
}
