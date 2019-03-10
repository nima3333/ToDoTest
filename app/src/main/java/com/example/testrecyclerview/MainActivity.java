package com.example.testrecyclerview;

import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MyRecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    private ItemTouchHelper itemTouchHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initSwipe();
    }

    private void initViews(){
        // set up the RecyclerView
        recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);

        // data to populate the RecyclerView with
        ArrayList<String> animalNames = new ArrayList<>();
        animalNames.add("1");
        animalNames.add("2");
        animalNames.add("3");
        animalNames.add("4");
        animalNames.add("5");

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT) {

                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder source, RecyclerView.ViewHolder target) {
                        adapter.moveItem(source.getAdapterPosition(), target.getAdapterPosition());
                        return true;
                    }

                    @Override
                    public boolean isLongPressDragEnabled(){
                        return false;
                    }
                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                        adapter.removeItem(viewHolder.getAdapterPosition());
                    }

                };

        itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);

        adapter = new MyRecyclerViewAdapter(animalNames, itemTouchHelper);
        //adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        // separator
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), mLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        /**
         * animalNames.add("Goat22");
         * adapter.notifyDataSetChanged();
         **/

    }

    private void initSwipe(){

        Toast.makeText(MainActivity.this, "Init", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {

    }


}
