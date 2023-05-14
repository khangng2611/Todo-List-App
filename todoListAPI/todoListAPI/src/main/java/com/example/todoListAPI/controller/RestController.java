package com.example.todoListAPI.controller;

import com.example.todoListAPI.entities.Task;
import com.example.todoListAPI.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {
    @Autowired
    private TaskService taskService;
    @RequestMapping("/tasks")
    public List<Task> getAllTask() {
        return taskService.getAllTask();
    }
    @RequestMapping("/tasks/{id}")
    public Task getTask(@PathVariable("id") int id) {
        return taskService.getTask(id);
    }
    @PostMapping("/tasks/add")
    public void addTask(@RequestBody Task newTask) {
        taskService.addTask(newTask);
    }
    @PutMapping("/tasks/update")
    public void updateTask(@RequestBody Task newTask) {
        taskService.updateTask(newTask);
    }
    @DeleteMapping("/tasks/delete/{id}")
    public void deleteTask(@PathVariable("id") int id) {
        taskService.deleteTask(id);
    }
}
