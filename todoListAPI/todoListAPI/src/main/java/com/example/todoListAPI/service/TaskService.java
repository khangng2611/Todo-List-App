package com.example.todoListAPI.service;

import com.example.todoListAPI.dao.TaskDAO;
import com.example.todoListAPI.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskService {
    @Autowired
    private TaskDAO taskDAO;
    public List<Task> getAllTask() {
        return taskDAO.getAllTask();
    }
    public Task getTask(int id) {
        return taskDAO.getTask(id);
    }
    public void addTask(Task newTask) {
        taskDAO.addTask(newTask);
    }
    public void updateTask(Task newTask) {
        taskDAO.updateTask(newTask);
    }
    public void deleteTask(int deleteId) {
        taskDAO.deleteTask(deleteId);
    }
}
