package com.example.todolist;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.todolist.Model.ToDoModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import com.example.todolist.Data.ToDoList;

public class AddNewTask extends BottomSheetDialogFragment {
    public static final String TAG = "ActionBottomDialog";
    private ToDoModel toDoModel = null;
    private Callback callback = null;

    private EditText newTaskText;
    private Button newTaskSaveButton;


    public AddNewTask(ToDoModel toDoModel){
        this.toDoModel = toDoModel;
    }
    public AddNewTask(ToDoModel toDoModel, Callback callback){
        this.toDoModel = toDoModel;
        this.callback = callback;
    }
    public AddNewTask(Callback callback){
        this.callback = callback;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.DialogStyle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_task, container, false);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        newTaskText = getView().findViewById(R.id.newTaskText);
        newTaskSaveButton= getView().findViewById(R.id.newTaskButton);

        boolean isUpdate = this.toDoModel != null;
        if(isUpdate){
            String task = toDoModel.getTask();
            newTaskText.setText(task);
            if (task.length() > 0)
                newTaskSaveButton.setTextColor(ContextCompat.getColor(getContext(),R.color.white));
        }

        newTaskText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().equals("")) {
                    newTaskSaveButton.setEnabled(false);
                    newTaskSaveButton.setTextColor(Color.RED);
                } else {
                    newTaskSaveButton.setEnabled(true);
                    newTaskSaveButton.setTextColor(ContextCompat.getColor(getContext(),R.color.white));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        boolean finalIsUpdate = isUpdate;
        newTaskSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = newTaskText.getText().toString();
                if (isUpdate)
                    toDoModel.setTask(text);
                else {
                    ToDoModel newToDoModel = new ToDoModel(0,false,text);
                    ToDoList.addTask(newToDoModel);
                }
                callback.changedCallback();
                dismiss();
            }
        });
    }

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        callback.dismissCallback();
        super.onCancel(dialog);
    }

    public interface Callback {
        public void changedCallback();
        public void dismissCallback();
    }
}
