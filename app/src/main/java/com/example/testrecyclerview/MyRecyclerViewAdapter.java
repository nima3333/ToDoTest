package com.example.testrecyclerview;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.support.design.widget.TextInputLayout;
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

import java.util.Collections;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<ListItem> mData;
    private ItemTouchHelper mTouchHelper;

    // data is passed into the constructor
    MyRecyclerViewAdapter(List<ListItem> data, ItemTouchHelper touchHelper) {
        this.mTouchHelper = touchHelper;
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
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
        return mData.get(position).getListItemType();
    }

    // binds the data to the TextView in each row
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType){
            case ListItem.TYPE_A:
                final Task task = (Task)mData.get(position);
                final ViewHolderA temp = (ViewHolderA) holder;
                String text = task.getName();
                temp.getMyTextView().setText(text);
                temp.getImage().setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                            mTouchHelper.startDrag(temp);
                        }
                        return false;
                    }
                });
                temp.getmCheckBox().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
    public class ViewHolderA extends ViewHolder implements View.OnClickListener {
        private TextView myTextView;
        private ImageView image;
        private CheckBox mCheckBox;
        ViewHolderA(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.tvAnimalName);
            image = itemView.findViewById(R.id.imageView);
            mCheckBox = itemView.findViewById(R.id.cb);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
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
