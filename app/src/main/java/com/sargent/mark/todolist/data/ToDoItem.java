package com.sargent.mark.todolist.data;

/**
 * Created by mark on 7/4/17.
 */

public class ToDoItem {
    private String description;
    private String dueDate;
 //  Boolean Variable for task weather the task is completed or not
    private boolean completed;
 //  /string variable to store the value for the category of the task.
    private String category;

    public ToDoItem(String description, String dueDate) {
        this.description = description;
        this.dueDate = dueDate;
    }
//  Modifying the constructor to add the two variables completed and category
    public ToDoItem(String description, String dueDate,boolean completed,String category) {
        this.description = description;
        this.dueDate = dueDate;
        this.category = category;
        this.completed = completed;
    }
// Start of  Getter and Setter method for private variable completed and category
    public boolean isCompleted() { return completed; }

    public void setCompleted(boolean completed) { this.completed = completed; }

    public String getCategory() {return category; }

    public void setCategory(String category) { this.category = category;    }
// End of getter and setter for the private variable completed and category

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
