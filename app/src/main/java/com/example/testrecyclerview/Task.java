package com.example.testrecyclerview;

public class Task {

    private String name = "Default";
    private Boolean status = Boolean.FALSE;

    Task(String name, Boolean status){
        this.status = status;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
