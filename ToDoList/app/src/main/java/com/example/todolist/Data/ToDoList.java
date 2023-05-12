package com.example.todolist.Data;

import com.example.todolist.Model.ToDoModel;

import java.util.ArrayList;
import java.util.List;

public class ToDoList {
    private static List<ToDoModel> toDoList;

    public static void createFakeData() {
        ToDoList.toDoList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ToDoModel a = new ToDoModel(i, false, "Task number " + i);
            ToDoList.toDoList.add(a);
        }
    }

    public static List<ToDoModel> getToDoList() {
        return toDoList;
    }

    public static void addTask(ToDoModel toDoModel) {
        ToDoList.toDoList.add(toDoModel);
    }

    public static void deleteTask(int position) {
        toDoList.remove(position);
    }

    public static void deleteTask(ToDoModel toDoModel) {
        toDoList.remove(toDoModel);
    }
}
