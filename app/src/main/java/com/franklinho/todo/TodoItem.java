package com.franklinho.todo;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by franklinho on 1/5/16.
 */
@Table(name="TodoItems")
public class TodoItem extends Model implements Serializable {
    @Column(name = "Name", index=true)
    public String name;

    @Column(name = "Priority", index=true)
    public int priority;

    @Column(name = "Notes")
    public String notes;

    @Column(name = "Completed", index=true)
    public boolean completed;

    @Column(name = "DueDate", index=true)
    public Date dueDate;

//    @Column(name = "DueDateAdded", index=true)
//    public boolean dueDateAdded;


    public TodoItem() {
        super();
    }

    public TodoItem(String name, int priority, String notes, boolean completed, Date dueDate) {
        super();
        this.name = name;
        this.priority = priority;
        this.notes = notes;
        this.completed = completed;
        this.dueDate = dueDate;
//        this.dueDateAdded = dueDateAdded;
    }


}
