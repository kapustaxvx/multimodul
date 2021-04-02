package com.example.application.service;

import com.example.api.TaskClient;
import com.example.api.beans.Task;
import com.example.api.beans.TaskBuilder;
import com.example.application.dao.TaskDAO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class TaskService implements TaskClient {

    private TaskDAO taskDAO;

    public TaskService(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    @Override
    public Task createTask(Long userId, Task task) {
        return defaultTask("Gleb");
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
        return defaultTask("Ilia");
    }

    @Override
    public List<Task> getAllTasksOfUser(Long userId) {
        final ArrayList<Task> list = new ArrayList<>();
        list.add(defaultTask("Jendos"));
        list.add(defaultTask("Julia"));
        return list;
    }

    private Task defaultTask(String author){
        return TaskBuilder.create()
                .withTaskId(new Random().nextLong())
                .withTitle("Кушать")
                .withAuthor(author)
                .build();
    }
}
