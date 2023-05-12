package com.example.todolist.Model;

import java.io.Serializable;

public class ToDoModel implements Serializable {
    private int id;
    private boolean status;
    private String task;
    public ToDoModel(int id, boolean status, String task) {
        setId(id);
        setStatus(status);
        setTask(task);
    }
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTask() {
        return task;
    }

    public void toggle() {
        this.status = !this.status;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
