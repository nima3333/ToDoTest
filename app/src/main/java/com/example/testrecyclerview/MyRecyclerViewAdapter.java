package com.example.testrecyclerview;

import android.annotation.SuppressLint;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private ArrayList<Task> taskList;
    private ItemTouchHelper itemTouchHelper;
    private Context context;
    private int indice;

    // data is passed into the constructor
    MyRecyclerViewAdapter(ArrayList<Task> data, ItemTouchHelper touchHelper, Context context, int indice) {
        this.itemTouchHelper = touchHelper;
        this.taskList = data;
        this.context = context;
        this.indice = indice;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case ListItem.TYPE_A:
                view = LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.row_layout, parent, false);
                return new ViewHolderA(view);
            case ListItem.TYPE_B:
                view = LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.add_layout, parent, false);
                return new ViewHolderB(view);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return taskList.get(position).getListItemType();
    }

    // binds the data to the TextView in each row
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType){
            case ListItem.TYPE_A:
                final Task task = (Task)taskList.get(position);
                final ViewHolderA temp = (ViewHolderA) holder;
                String text = task.getName();
                Boolean state = task.getState();
                temp.getMyTextView().setText(text + state.toString());
                if (state) {
                    temp.myTextView.setPaintFlags(temp.myTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }

                temp.getmCheckBox().setOnCheckedChangeListener(null);
                temp.getmCheckBox().setChecked(state);
                temp.getImage().setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                            itemTouchHelper.startDrag(temp);
                        }
                        return false;
                    }
                });

                temp.getmCheckBox().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        int i = temp.getAdapterPosition();
                        int target = (isChecked ? 1 : 0) * (taskList.size()-1);
                        if (isChecked) {
                            temp.myTextView.setPaintFlags(temp.myTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        }
                        else{
                            temp.myTextView.setPaintFlags(temp.myTextView.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
                        }
                        ((Task)taskList.get(temp.getAdapterPosition())).setState(isChecked);
                        taskList.add(target, taskList.remove(temp.getAdapterPosition()));
                        FileHelper.writeData(taskList, context, indice);
                        notifyItemMoved(i, target);
                    }
                });


        }
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public void moveItem(int oldPos, int newPos) {
        //Collections.swap(taskList, oldPos, newPos);
        //notifyItemMoved(oldPos, newPos);
        if (oldPos < newPos) {
            for (int i = oldPos; i < newPos; i++) {
                Collections.swap(taskList, i, i + 1);
            }
        } else {
            for (int i = oldPos; i > newPos; i--) {
                Collections.swap(taskList, i, i - 1);
            }
        }
        FileHelper.writeData(taskList, context, indice);
        notifyItemMoved(oldPos, newPos);
    }

    public void removeItem(int position) {
        taskList.remove(position);
        FileHelper.writeData(taskList, context, indice);
        notifyItemRemoved(position);
        //notifyItemRangeChanged(position, taskList.size());
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolderA extends ViewHolder {
        private TextView myTextView;
        private ImageView image;
        private CheckBox mCheckBox;
        ViewHolderA(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.tvAnimalName);
            image = itemView.findViewById(R.id.imageView);
            mCheckBox = itemView.findViewById(R.id.cb);
        }

        public TextView getMyTextView() {
            return myTextView;
        }

        public ImageView getImage() {
            return image;
        }

        public CheckBox getmCheckBox() {
            return mCheckBox;
        }


    }

    public class ViewHolderB extends ViewHolder {
        EditText editText;
        ViewHolderB(View itemView) {
            super(itemView);
            editText = itemView.findViewById(R.id.editText);
        }
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
