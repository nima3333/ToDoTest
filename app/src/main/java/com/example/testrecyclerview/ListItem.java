package com.example.testrecyclerview;

import java.io.Serializable;

public interface ListItem extends Serializable {
    int TYPE_A = 1;
    int TYPE_B = 2;

    int getListItemType();
}