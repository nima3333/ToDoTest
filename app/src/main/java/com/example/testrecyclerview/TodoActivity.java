package com.example.testrecyclerview;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TodoActivity extends AppCompatActivity implements View.OnClickListener {

    private MyRecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    private ItemTouchHelper itemTouchHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initViews();
    }

    private void initViews(){
        // set up the RecyclerView
        recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);

        // data to populate the RecyclerView with
        ArrayList<ListItem> taskList = new ArrayList<>();
        taskList.add(new Task("1Aaa"));
        taskList.add(new Task("2Bbb"));
        taskList.add(new Task("3Ccc"));
        taskList.add(new Task("4Eee"));
        taskList.add(new Task("5Fff"));

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

        adapter = new MyRecyclerViewAdapter(taskList, itemTouchHelper);
        recyclerView.setAdapter(adapter);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        // separator
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), mLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        final EditText edittext = findViewById(R.id.editText);
        final ArrayList<ListItem> temp = taskList;
        edittext.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((actionId & EditorInfo.IME_MASK_ACTION) != 0) {
                    Toast.makeText(TodoActivity.this, edittext.getText(), Toast.LENGTH_SHORT).show();
                    temp.add(0, new Task(edittext.getText().toString()));
                    adapter.notifyItemInserted(0);
                    edittext.setText("");
                    edittext.clearFocus();
                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    return true;
                }
                else {
                    return false;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        Intent myIntent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}

