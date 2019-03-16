package com.example.testrecyclerview;

public class Task implements ListItem{
    private String name;
    private boolean state;

    public Task(String name, boolean state){
        this.name = name;
        this.state = state;
    }

    public Task(String name){
        this.name = name;
        this.state = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public int getListItemType() {
        return ListItem.TYPE_A;
    }
}
