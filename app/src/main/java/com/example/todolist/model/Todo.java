package com.example.todolist.model;

import java.util.Date;

public class Todo {
    private String id ;
    private String name;
    private boolean status ;
    private String detailTodo;
    private long time;
    private int user ;
    private String timeStamp;
    private String day;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Todo(String name, boolean status, String detailTodo, long time, int user) {
        this.name = name;
        this.status = status;
        this.detailTodo = detailTodo;
        this.time = time;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDetailTodo() {
        return detailTodo;
    }

    public void setDetailTodo(String detailTodo) {
        this.detailTodo = detailTodo;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
