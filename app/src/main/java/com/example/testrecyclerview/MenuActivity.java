package com.example.testrecyclerview;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().hide();

        LinearLayout vue1 = findViewById(R.id.vue1);
        LinearLayout vue2 = findViewById(R.id.vue2);
        LinearLayout vue3 = findViewById(R.id.vue3);
        LinearLayout vue4 = findViewById(R.id.vue4);

        vue1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), TodoActivity.class);
                myIntent.putExtra("vue", 0);
                //myIntent.putExtra("data", new Gson().toJson(FileHelper.readData(getApplicationContext())));
                startActivityForResult(myIntent, 0);
            }
        });

        vue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), TodoActivity.class);
                myIntent.putExtra("vue", 1);
                //myIntent.putExtra("data", new Gson().toJson(FileHelper.readData(getApplicationContext())));
                startActivityForResult(myIntent, 0);
            }
        });

        vue3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), TodoActivity.class);
                myIntent.putExtra("vue", 2);
                //myIntent.putExtra("data", new Gson().toJson(FileHelper.readData(getApplicationContext())));
                startActivityForResult(myIntent, 0);
            }
        });

        vue4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), TodoActivity.class);
                myIntent.putExtra("vue", 3);
                //myIntent.putExtra("data", new Gson().toJson(FileHelper.readData(getApplicationContext())));
                startActivityForResult(myIntent, 0);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //super.onCreate(savedInstanceState);


        ArrayList<Integer> liste = FileHelper.getSize(getApplicationContext());
        TextView tv1 = findViewById(R.id.textView1);
        TextView tv2 = findViewById(R.id.textView2);
        TextView tv3 = findViewById(R.id.textView3);
        TextView tv4 = findViewById(R.id.textView4);
        tv1.setText(liste.get(0).toString());
        tv2.setText(liste.get(1).toString());
        tv3.setText(liste.get(2).toString());
        tv4.setText(liste.get(3).toString());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            finish();
            System.exit(0);
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
