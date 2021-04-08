package com.example.api;

import com.example.api.beans.AssignRequest;
import com.example.api.beans.Task;

import java.util.List;

public interface TaskClient {

    Task createTask(Task task);

    Task updateTask(Long taskId, Task task);

    void deleteTask(Long taskId);

    Task getTaskById(Long taskId);

    List<Task> getAllTasksOfUser(Long userId);

    void assignTask(Long taskId, AssignRequest request);
}
