package com.example.testrecyclerview;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<Task> mData;
    private ItemTouchHelper mTouchHelper;

    // data is passed into the constructor
    MyRecyclerViewAdapter(List<Task> data, ItemTouchHelper touchHelper) {
        this.mTouchHelper = touchHelper;
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Task task = mData.get(position);
        String animal = task.getName();
        holder.myTextView.setText(animal);
        final ViewHolder temp = holder;
        ((ViewHolder) holder).image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                    mTouchHelper.startDrag(temp);
                }
                return false;
            }
        });
        ((ViewHolder) holder).mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                task.setState(isChecked);
                if(isChecked == true) {
                    temp.myTextView.setPaintFlags(temp.myTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else{
                    temp.myTextView.setPaintFlags(temp.myTextView.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                }
            }
        });


    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void moveItem(int oldPos, int newPos) {
        Collections.swap(mData, oldPos, newPos);
        notifyItemMoved(oldPos, newPos);
    }

    public void removeItem(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mData.size());
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        ImageView image;
        CheckBox mCheckBox;
        int position;
        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.tvAnimalName);
            image = itemView.findViewById(R.id.imageView);
            mCheckBox = itemView.findViewById(R.id.cb);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
        }

    }

    // convenience method for getting data at click position
    public String getItem(int id) {
        return mData.get(id).getName();
    }


    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
