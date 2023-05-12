package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.todolist.Adapter.ToDoAdapter;
import com.example.todolist.Data.ToDoList;
import com.example.todolist.Model.ToDoModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private ToDoAdapter tasksAdapter;
    RecyclerView tasksRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        ToDoList.createFakeData();

        tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        tasksRecyclerView.setLayoutManager(layoutManager);
        tasksAdapter = new ToDoAdapter(MainActivity.this);
        tasksRecyclerView.setAdapter(tasksAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerItemTouchHelper(tasksAdapter));
        itemTouchHelper.attachToRecyclerView(tasksRecyclerView);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewTask dialog = new AddNewTask(new AddNewTask.Callback() {
                    @Override
                    public void changedCallback() {
                        int newTaskPosition = ToDoList.getToDoList().size();
                        tasksAdapter.notifyItemInserted(newTaskPosition);
                    }

                    @Override
                    public void dismissCallback() {

                    }
                });
                dialog.show(getSupportFragmentManager(), AddNewTask.TAG);
            }
        });
    }
}