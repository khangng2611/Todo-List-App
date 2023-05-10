package com.example.todolist.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.AddNewTask;
import com.example.todolist.MainActivity;
import com.example.todolist.Model.ToDoModel;
import com.example.todolist.R;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {
    private final List<ToDoModel> todoList;
    private final MainActivity activity;
    public ToDoAdapter(MainActivity activity) {
        this.activity = activity;
        todoList = activity.toDoList;
    }
    public Context getContext() {
        return this.activity;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_layout,parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        System.out.println(position);
        ToDoModel toDoModel = this.todoList.get(position);
        holder.checkbox.setText(toDoModel.getTask());
        holder.checkbox.setChecked(toDoModel.getStatus());
        holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                toDoModel.toggle();
            }
        });
        holder.checkbox.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                editItem(holder.getAbsoluteAdapterPosition());
                return false;
            }
        });
    }
    public void editItem(int position){
        AddNewTask dialog =  AddNewTask.newInstance();
        Bundle bundle = new Bundle();
//                AddNewTask.Callback callback =
        bundle.putSerializable("callback", new AddNewTask.Callback() {
            @Override
            public void callback() {
                activity.onDialogDismiss();
            }
        });
        bundle.putSerializable("toDoModel",todoList.get(position));
        dialog.setArguments(bundle);
        dialog.show(activity.getSupportFragmentManager(), AddNewTask.TAG);
    }

    public void removeItem(int position){
        this.todoList.remove(position);
        this.notifyItemRemoved(position);
    }
    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkbox;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkbox = itemView.findViewById(R.id.todoCheckBox);
        }
    }
}
