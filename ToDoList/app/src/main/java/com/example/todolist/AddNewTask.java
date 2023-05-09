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

import java.io.Serializable;

public class AddNewTask extends BottomSheetDialogFragment {
    public static final String TAG = "ActionBottomDialog";
    public ToDoModel toDoModel = null;
    public  Callback callback = null;

    private EditText newTaskText;
    private Button newTaskSaveButton;

    public static AddNewTask newInstance(){
        return new AddNewTask();
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

        boolean isUpdate = false;
        final Bundle bundle = getArguments();
        if (bundle != null) {
            toDoModel = (ToDoModel) bundle.getSerializable("toDoModel");
            callback = (Callback) bundle.getSerializable("callback");
            if(toDoModel != null)
            {
                isUpdate = true;
                String task = toDoModel.getTask();
                newTaskText.setText(task);
                if (task.length() > 0)
                    newTaskSaveButton.setTextColor(ContextCompat.getColor(getContext(),R.color.white));
            }
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
                if (finalIsUpdate && toDoModel != null)
                    toDoModel.setTask(text);
                else {
                    ToDoModel addNew = new ToDoModel(0,0,text);
                    ((MainActivity) getActivity()).toDoList.add(addNew);
                }
                dismiss();
            }
        });
    }

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        callback.callback();
        super.onCancel(dialog);
    }

    @Override
    public void dismiss() {
        callback.callback();
        super.dismiss();
    }

    public interface Callback extends Serializable {
        public void callback();
    }
}
