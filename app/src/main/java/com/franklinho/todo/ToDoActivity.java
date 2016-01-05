package com.franklinho.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.activeandroid.query.Select;

import java.util.List;

public class ToDoActivity extends AppCompatActivity {

//    ArrayList<TodoItem> todoItems;
    List<TodoItem> queryResults;
//    ArrayList<String> todoItems;
    ToDoItemsAdapter aToDoAdapter;
//    ArrayAdapter<String> aToDoAdapter;
    ListView lvItems;
    EditText etEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);

        // Views are create din the layout, find the references in the view
//        queryResults = new List<TodoItem>();
//        aToDoAdapter = new ToDoItemsAdapter(this, queryResults);
        populateArrayItems();
        lvItems = (ListView) findViewById(R.id.lvItems);
        etEditText = (EditText) findViewById(R.id.etEditText);
        lvItems.setAdapter(aToDoAdapter);
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                todoItems.remove(position);
//                writeItems();
//
                TodoItem removeItem = queryResults.get(position);
                aToDoAdapter.remove(removeItem);
                removeItem.delete();
                queryResults.remove(removeItem);
                aToDoAdapter.notifyDataSetChanged();
                return true;
            }
        });
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ToDoActivity.this, EditItemActivity.class);
                i.putExtra("position", position);
                i.putExtra("todoItemText", queryResults.get(position).name.toString());
                startActivityForResult(i, 1);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 1) {
            String name = data.getExtras().getString("todoItemText");
            int position = data.getExtras().getInt("position");
//            todoItems.set(position, name.toString());
            TodoItem updateItem = queryResults.get(position);
            updateItem.name = name;
            updateItem.save();
            int code = data.getExtras().getInt("code", 0);
//            writeItems();
            aToDoAdapter.notifyDataSetChanged();
        }
    }

    public void populateArrayItems() {
//        todoItems = new ArrayList<String>();
        readItems();
//        aToDoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todoItems);
//        aToDoAdapter = new ToDoItemsAdapter(this, todoItems);
    }



    public void onAddItem(View view) {

//        writeItems();
        TodoItem addItem = new TodoItem();
        addItem.name = etEditText.getText().toString();
        addItem.save();
//        aToDoAdapter.add(addItem);
        queryResults.add(addItem);
        etEditText.setText("");

    }

    private void readItems() {
//        File filesDir = getFilesDir();
//        File file = new File(filesDir, "todo.txt");
//        try {
//            todoItems = new ArrayList<String>(FileUtils.readLines(file));

//        } catch (IOException e) {
//
//        }
        queryResults = new Select().from(TodoItem.class).execute();

        aToDoAdapter = new ToDoItemsAdapter(this, queryResults);

//        todoItems = new ArrayList<String>();
//        for (TodoItem result : queryResults) {
//            todoItems.add((String) result.name);
//        }

    }

    private void writeItems() {
//        File filesDir = getFilesDir();
//        File file = new File(filesDir, "todo.txt");
//        try {
//            FileUtils.writeLines(file, todoItems);
//        } catch (IOException e) {
//
//        }

    }
}

