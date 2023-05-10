package com.example.todolist;

import android.app.AlertDialog;
import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.Adapter.ToDoAdapter;

public class RecyclerItemTouchHelper extends ItemTouchHelper.SimpleCallback {
    private ToDoAdapter toDoAdapter;
    public RecyclerItemTouchHelper(ToDoAdapter toDoAdapter) {
        super(0,ItemTouchHelper.LEFT);
        this.toDoAdapter = toDoAdapter;
    }
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        final int position = viewHolder.getAbsoluteAdapterPosition();
        if (direction == ItemTouchHelper.LEFT) {
            AlertDialog.Builder builder = new AlertDialog.Builder(toDoAdapter.getContext());
            builder.setTitle("Delete Task");
            builder.setMessage("Are you sure to delete this task ?");
            builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    toDoAdapter.removeItem(position);
                }
            });
            builder.setNegativeButton(android.R.string.cancel, null);
            builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    toDoAdapter.notifyItemChanged(viewHolder.getAbsoluteAdapterPosition());
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
//            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//                @Override
//                public void onDismiss(DialogInterface dialogInterface) {
//                    toDoAdapter.notifyItemChanged(viewHolder.getAbsoluteAdapterPosition());
//                }
//            });
        }
    }
}
