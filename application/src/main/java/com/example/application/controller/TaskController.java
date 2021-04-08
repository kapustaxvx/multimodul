package com.example.application.controller;

import com.example.api.beans.AssignRequest;
import com.example.api.beans.Task;
import com.example.application.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }


    @PostMapping(value = "/{taskId}/assign")
    void assignTask(@PathVariable(value = "taskId") Long taskId, @RequestBody AssignRequest assignRequest) {
        taskService.assignTask(taskId, assignRequest);
    }

    @PutMapping(value = "/{taskId}")
    Task updateTask(@PathVariable(value = "taskId") Long taskId, @RequestBody Task task) {
        return taskService.updateTask(taskId, task);
    }

    @GetMapping(value = "/{taskId}")
    Task getTaskById(@PathVariable(value = "taskId") Long taskId) {
        return taskService.getTaskById(taskId);
    }


    @GetMapping
    List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

}
