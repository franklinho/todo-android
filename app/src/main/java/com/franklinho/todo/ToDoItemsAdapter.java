package com.franklinho.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by franklinho on 1/5/16.
 */
public class ToDoItemsAdapter extends ArrayAdapter<TodoItem> {
    public ToDoItemsAdapter(Context context, List<TodoItem> todoitems) {
        super(context, 0, todoitems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TodoItem todoitem = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.todo_item_layout, parent, false);
        }

        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvPriority = (TextView) convertView.findViewById(R.id.tvPriority);
        TextView tvDueDate = (TextView) convertView.findViewById(R.id.tvDueDate);
        tvDueDate.setText("");

        tvName.setText(todoitem.name);
        String priorityString;
        switch (todoitem.priority) {
            case 1: priorityString = "High";
                tvPriority.setBackgroundColor(0xFFfa8072);
                break;
            case 2: priorityString = "Medium";
                tvPriority.setBackgroundColor(0xFFfff68f);
                break;
            case 3: priorityString = "Low";
                tvPriority.setBackgroundColor(0xFF20b2aa);
                break;
            default: priorityString = "Medium";
                tvPriority.setBackgroundColor(0xFFfff68f);
                break;
        }

        tvPriority.setText(priorityString);
        if (todoitem.dueDate != null) {
//            if (todoitem.dueDateAdded == true) {
                SimpleDateFormat dateformat = new SimpleDateFormat("MMM/dd/yyyy");
                tvDueDate.setText(dateformat.format(todoitem.dueDate));
//            } else {
//                tvDueDate.setText("");
//            }
        }

        return convertView;
    }
}
