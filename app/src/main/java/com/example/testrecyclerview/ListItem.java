package com.example.testrecyclerview;

import java.io.Serializable;

public class ListItem implements Serializable {
    final static int TYPE_A = 1;
    final static int TYPE_B = 2;

    public ListItem(){
    }

    int getListItemType(){
        return 0;
    }
}