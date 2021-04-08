package com.example.application.service;

import com.example.api.TaskClient;
import com.example.api.beans.AssignRequest;
import com.example.api.beans.Task;
import com.example.api.beans.TaskBuilder;
import com.example.api.beans.TaskStatus;
import com.example.application.dao.TaskDAO;
import com.example.application.dao.TaskStatusesDAO;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class TaskService implements TaskClient {

    private final TaskDAO taskDAO;
    private final TaskStatusesDAO taskStatusesDAO;

    public TaskService(TaskDAO taskDAO, TaskStatusesDAO taskStatusesDAO) {
        this.taskDAO = taskDAO;
        this.taskStatusesDAO = taskStatusesDAO;
    }

    @Override
    public Task createTask(Task task) {
        return taskDAO.createTask(task);
    }

    @Override
    public void assignTask(Long taskId, AssignRequest request) {
        taskDAO.assignTask(taskId, request.getUserId());
    }


    @Override
    public Task updateTask(Long taskId, Task task) {
        return defaultTask("Alima");
    }

    @Override
    public void deleteTask(Long taskId) {

    }

    @Override
    public Task getTaskById(Long taskId) {
        return taskDAO.getTaskById(taskId);
    }

    @Override
    public List<Task> getAllTasksOfUser(Long userId) {
        final ArrayList<Task> list = new ArrayList<>();
        list.add(defaultTask("Jendos"));
        list.add(defaultTask("Julia"));
        return list;
    }


    private Task defaultTask(String author) {
        return TaskBuilder.create()
                .withTaskId(new Random().nextLong())
                .withStatus(TaskStatus.NEW)
                .withCreationDate(Instant.now())
                .withTitle("Кушать")
                .withExpirationDate(Instant.now().plusSeconds(500000000L))
                .build();
    }
}
