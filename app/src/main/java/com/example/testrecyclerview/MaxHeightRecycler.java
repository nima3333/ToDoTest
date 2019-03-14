package com.example.testrecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class MaxHeightRecycler extends RecyclerView {

    public MaxHeightRecycler(@NonNull Context context) {
        super(context);
    }

    public MaxHeightRecycler(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        heightSpec = MeasureSpec.makeMeasureSpec(500, MeasureSpec.AT_MOST);
        super.onMeasure(widthSpec, heightSpec);
    }
}
