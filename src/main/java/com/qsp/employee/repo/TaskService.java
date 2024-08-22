package com.qsp.employee.repo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qsp.employee.model.Task;
import com.qsp.employee.repo.TaskRepository;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

//    public Task updateTask(Long id, Task taskDetails) {
//        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
//        task.setDescription(taskDetails.getTaskDiscription());
//        task.setUser(taskDetails.getAssignUser());
//        task.setProject(taskDetails.getProject());
//        return taskRepository.save(task);
//    }

    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        taskRepository.delete(task);
    }
}

