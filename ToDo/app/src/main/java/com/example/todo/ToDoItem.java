package com.example.todo;

import androidx.annotation.NonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ToDoItem {

    private String name;
    private LocalDate date;
    private Boolean done;
    private final DateTimeFormatter dTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public ToDoItem(String name, LocalDate date) {
        this.name = name;
        this.date = date;
        this.done = false;
    }

    public ToDoItem(String name) {
        this.name = name;
        this.done = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date.format(dTF);
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    @NonNull
    @Override
    public String toString() {
        if (date == null || done == null) {
            return name;
        }
        String doneStr = "To do";
        if(done) {
            doneStr = "Done";
        }
        return name + ", " + getDate() + ", " + doneStr;
    }
}
