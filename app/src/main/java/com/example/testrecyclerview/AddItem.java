package com.example.testrecyclerview;

public class AddItem implements ListItem{
    private String name;

    public AddItem(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getListItemType() {
        return ListItem.TYPE_B;
    }
}
