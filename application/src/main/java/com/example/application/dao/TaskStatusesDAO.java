package com.example.application.dao;

import com.example.api.beans.TaskStatus;

public class TaskStatusesDAO {

    public Integer provideIdentifier(TaskStatus status){
        return 1;           //// TODO: 07.04.2021
    }

    public TaskStatus getTaskStatus(Integer id){
        return TaskStatus.NEW;        //// TODO: 07.04.2021
    }
}
