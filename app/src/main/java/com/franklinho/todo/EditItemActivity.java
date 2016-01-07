package com.franklinho.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class EditItemActivity extends AppCompatActivity {
    EditText etToDoName;
    int position;
    TodoItem todoitem;
    EditText etNotes;
    DatePicker dpDueDate;
    Spinner spnPriority;
    Spinner spnStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String todoItemText = getIntent().getStringExtra("todoItemText");
        position = getIntent().getIntExtra("position", 0);
        todoitem = (TodoItem) getIntent().getSerializableExtra("ToDoItem");
        Toast.makeText(this, Integer.toString(position), Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        etToDoName = (EditText) findViewById(R.id.etToDoName);
        etNotes = (EditText) findViewById(R.id.etNotes);
        dpDueDate = (DatePicker) findViewById(R.id.dpDueDate);
        spnPriority = (Spinner) findViewById(R.id.spnPriority);
        spnStatus = (Spinner) findViewById(R.id.spnStatus);
//        etToDoName.setText(todoItemText);
        etToDoName.setText(todoitem.name);
        if (todoitem.notes != null) {
            etNotes.setText(todoitem.notes);
        }
        if (todoitem.dueDate != null) {
            dpDueDate.updateDate(todoitem.dueDate.getYear(),todoitem.dueDate.getMonth(),todoitem.dueDate.getDay());
        }

        if (todoitem.completed != false) {
                spnStatus.setSelection(1);
        }

        switch (todoitem.priority) {
            case 1: spnPriority.setSelection(0);
                break;
            case 2: spnPriority.setSelection(1);
                break;
            case 3: spnPriority.setSelection(2);
                break;
            default: spnPriority.setSelection(1);
                break;
        }
    }

    public void saveEditedItem(View view) {
        Intent data = new Intent();
        data.putExtra("todoItemText", etToDoName.getText().toString());
        data.putExtra("position", position);
        data.putExtra("notes", etNotes.getText().toString());

        switch (spnPriority.getSelectedItem().toString()) {
            case "High": data.putExtra("priority", 1);
                break;
            case "Medium": data.putExtra("priority", 2);
                break;
            case "Low": data.putExtra("priority", 3);
                break;
            default: data.putExtra("priority", 2);
                break;
        }

        switch (spnStatus.getSelectedItem().toString()) {
            case "To Do": data.putExtra("completed", false );
                break;
            case "Completed": data.putExtra("completed", true);
                break;
            default: data.putExtra("completed", false);
                break;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(dpDueDate.getYear(), dpDueDate.getMonth(), dpDueDate.getDayOfMonth(), 0, 0, 0);
        data.putExtra("dueDate", calendar.getTimeInMillis());
        data.putExtra("code",200);
        setResult(RESULT_OK, data);
//        todoitem.name = etToDoName.getText().toString();
//        todoitem.save();
        this.finish();
    }
}
