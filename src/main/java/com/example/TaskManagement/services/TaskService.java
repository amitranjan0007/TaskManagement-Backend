package com.example.TaskManagement.services;

import com.example.TaskManagement.model.Task;
import com.example.TaskManagement.request.TaskRequest;

import java.text.ParseException;
import java.util.List;

public interface TaskService {
    Task addTask(TaskRequest taskRequest) throws ParseException;
    List<Task> getAllTask();
    boolean updateTask(TaskRequest taskRequest) throws ParseException;
    boolean deleteTask(String taskId);
}
