package com.example.TaskManagement.services;

import com.example.TaskManagement.model.Task;
import com.example.TaskManagement.repository.TaskRepository;
import com.example.TaskManagement.request.TaskRequest;
import com.example.TaskManagement.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;


@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskRepository repository;

    @Override
    public Task addTask(TaskRequest request) throws ParseException {
        Date startDate=Utility.convertToDate(request.getStartDate());
        Date endDate=Utility.convertToDate(request.getEndDate());
        if (request.getTitle() == null || request.getTitle().trim().isEmpty() ||
                request.getAssignedTo() == null || request.getAssignedTo().trim().isEmpty() ||
                startDate == null || endDate == null){
            throw new IllegalArgumentException("Invalid Request: Missing Mandatory Field");
        }

        Utility.checkStartEndDateValid(request.getStartDate(),request.getEndDate());

        try{
            Task task = new Task(
                    null,
                    request.getTitle(),
                    request.getAssignedTo(),
                    request.getStatus(),
                    request.getPriority(),
                    Utility.formatDate(startDate),
                    Utility.formatDate(endDate)
            );
            return repository.save(task);
        }catch (Exception e){
            System.out.println("Error in adding task "+e.getMessage());
            throw new RuntimeException("Error in adding task");
        }
    }

    @Override
    public List<Task> getAllTask() {
        try{
            return repository.findAll();
        }catch (Exception e){
            throw new RuntimeException("Something went wrong");
        }

    }

    @Override
    public boolean updateTask(TaskRequest request) throws ParseException {

        Utility.checkStartEndDateValid(request.getStartDate(),request.getEndDate());

        Task task=repository.findById(request.getId()).get();
        if(task!=null){
           task.setTitle(request.getTitle());
           task.setAssignedTo(request.getAssignedTo());
           task.setStatus(request.getStatus());
           task.setPriority(request.getPriority());
           task.setStartDate(request.getStartDate());
           task.setEndDate(request.getEndDate());
           repository.save(task);
           return true;
        }
        return false;
    }

    @Override
    public boolean deleteTask(String taskId) {
        if(taskId==null || taskId.length()==0){
            throw new IllegalStateException("Invalid argument");
        }
        try{
            Long taskIdLong = Long.parseLong(taskId);
            repository.deleteById(taskIdLong);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
