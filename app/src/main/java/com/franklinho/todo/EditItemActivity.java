package com.franklinho.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditItemActivity extends AppCompatActivity {
    EditText etToDoName;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String todoItemText = getIntent().getStringExtra("todoItemText");
        position = getIntent().getIntExtra("position", 0);
        Toast.makeText(this, Integer.toString(position), Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        etToDoName = (EditText) findViewById(R.id.etToDoName);
        etToDoName.setText(todoItemText);
    }

    public void saveEditedItem(View view) {
        Intent data = new Intent();
        data.putExtra("todoItemText",etToDoName.getText().toString());
        data.putExtra("position", position);
        data.putExtra("code",200);
        setResult(RESULT_OK, data);
        this.finish();
    }
}
