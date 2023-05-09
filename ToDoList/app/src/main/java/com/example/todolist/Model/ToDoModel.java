package com.example.todolist.Model;

import java.io.Serializable;

public class ToDoModel implements Serializable {
    private int id, status;
    private String task;
    public ToDoModel(int id, int status, String task) {
        setId(id);
        setStatus(status);
        setTask(task);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getStatus() {
        return this.status == 1;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTask() {
        return task;
    }

    public void toggle() {
        this.status = 1-this.status;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
