package com.example.TaskManagement.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskRequest {
    Long id;
    String title;
    String assignedTo;
    String status;
    String priority;
    String startDate;
    String endDate;
}
