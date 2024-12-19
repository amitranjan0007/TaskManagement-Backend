package com.example.TaskManagement.controller;


import com.example.TaskManagement.model.Task;
import com.example.TaskManagement.request.TaskRequest;
import com.example.TaskManagement.response.ApiResponse;
import com.example.TaskManagement.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping("/health")
    public boolean healthCheck(){
        return true;
    }

    @GetMapping("/getAllTask")
    public ApiResponse<List<Task>> getAllTask(){
        try {
            List<Task> tasks=taskService.getAllTask();
            return new ApiResponse(
                    tasks, HttpStatus.OK.value(),"fetched the data", LocalDateTime.now()
            );
        }catch (Exception e){
            return new ApiResponse(
                    null, HttpStatus.INTERNAL_SERVER_ERROR.value(),"Unable to fetch data", LocalDateTime.now()
            );
        }
    }
    @PostMapping("/addTask")
    public ApiResponse<Task> addTask(@RequestBody TaskRequest request){
        try{
            Task task=taskService.addTask(request);
           return new ApiResponse<Task>(
                    task, HttpStatus.OK.value(),"added task", LocalDateTime.now()
            );
        }catch (IllegalArgumentException e){
            return new ApiResponse<Task>(
                    null, HttpStatus.BAD_REQUEST.value(),e.getMessage(),LocalDateTime.now()
            );
        }catch (Exception e){
            return new ApiResponse<Task>(
                    null, HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),LocalDateTime.now()
            );
        }
    }

    @PutMapping("/updateTask")
    public ApiResponse<Boolean> updateTask(@RequestBody TaskRequest request){

        try {
            if (request == null || request.getId() == null) {
                throw new IllegalArgumentException("Invalid request, task ID cannot be null");
            }

            boolean taskUpdated=taskService.updateTask(request);
            return new ApiResponse(
                    taskUpdated, HttpStatus.OK.value(),"Task Updated", LocalDateTime.now()
            );
        }catch (IllegalArgumentException e){
            return new ApiResponse<Boolean>(
                    false, HttpStatus.BAD_REQUEST.value(),"Bad Request", LocalDateTime.now()
            );

        }catch (Exception e){
            return new ApiResponse<Boolean>(
                    false, HttpStatus.INTERNAL_SERVER_ERROR.value(),"Not able to updated", LocalDateTime.now()
            );
        }
    }

    @DeleteMapping("/deleteTask")
    public ApiResponse<Boolean> deleteTask(@RequestParam String taskId){

        try {
            if (taskId == null || taskId.length()==0) {
                throw new IllegalArgumentException("Invalid taskId");
            }

            boolean taskUpdated=taskService.deleteTask(taskId);
            return new ApiResponse(
                    taskUpdated, HttpStatus.OK.value(),"Task deleted", LocalDateTime.now()
            );
        }catch (IllegalArgumentException e){
            return new ApiResponse<Boolean>(
                    false, HttpStatus.BAD_REQUEST.value(),"Bad Request", LocalDateTime.now()
            );

        }catch (Exception e){
            return new ApiResponse<Boolean>(
                    false, HttpStatus.INTERNAL_SERVER_ERROR.value(),"Not able to delete the task", LocalDateTime.now()
            );
        }
    }
}
