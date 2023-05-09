package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.todolist.Adapter.ToDoAdapter;
import com.example.todolist.Model.ToDoModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    public List<ToDoModel> toDoList ;
    private ToDoAdapter tasksAdapter;
    RecyclerView tasksRecyclerView;
    public void createFakeData () {
        this.toDoList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ToDoModel a = new ToDoModel(i,0,"Task number "+i);
            this.toDoList.add(a);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        createFakeData();
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
                AddNewTask dialog =  AddNewTask.newInstance();
                Bundle bundle = new Bundle();
                bundle.putSerializable("callback", new AddNewTask.Callback() {
                    @Override
                    public void callback() {
                        onDialogDismiss();
                    }
                });
                dialog.setArguments(bundle);
                dialog.show(getSupportFragmentManager(), AddNewTask.TAG);
            }
        });
    }

    public void onDialogDismiss() {
        tasksAdapter.notifyDataSetChanged();
    }
}