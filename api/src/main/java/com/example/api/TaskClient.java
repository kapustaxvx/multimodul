package com.example.api;

import com.example.api.beans.Task;

import java.util.List;

public interface TaskClient {

    Task createTask(Long userId, Task task);

    Task updateTask(Long taskId, Task task);

    void deleteTask(Long taskId);

    Task getTaskById(Long taskId);

    List<Task> getAllTasksOfUser(Long userId);
}
