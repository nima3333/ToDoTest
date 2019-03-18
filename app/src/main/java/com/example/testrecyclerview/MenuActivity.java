package com.example.testrecyclerview;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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

        //Listener sur les vues
        vue1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), TodoActivity.class);
                myIntent.putExtra("vue", 0);
                startActivityForResult(myIntent, 0);
            }
        });

        vue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), TodoActivity.class);
                myIntent.putExtra("vue", 1);
                startActivityForResult(myIntent, 0);
            }
        });

        vue3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), TodoActivity.class);
                myIntent.putExtra("vue", 2);
                startActivityForResult(myIntent, 0);
            }
        });

        vue4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), TodoActivity.class);
                myIntent.putExtra("vue", 3);
                startActivityForResult(myIntent, 0);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                Intent intent = new Intent(getApplicationContext(), Setting.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //super.onCreate(savedInstanceState);

        //Gestion des tailles des listes
        ArrayList<Integer> liste = FileHelper.getSize(getApplicationContext());
        TextView tv1 = findViewById(R.id.textView1);
        TextView tv2 = findViewById(R.id.textView2);
        TextView tv3 = findViewById(R.id.textView3);
        TextView tv4 = findViewById(R.id.textView4);
        tv1.setText(liste.get(0).toString());
        tv2.setText(liste.get(1).toString());
        tv3.setText(liste.get(2).toString());
        tv4.setText(liste.get(3).toString());

        //Gestion des pourcentages des donuts
        ArrayList<Float> percentages = FileHelper.getPercentages(getApplicationContext());
        com.github.lzyzsd.circleprogress.DonutProgress donut1 = findViewById(R.id.donut1);
        donut1.setProgress(percentages.get(0));
        com.github.lzyzsd.circleprogress.DonutProgress donut2 = findViewById(R.id.donut2);
        donut2.setProgress(percentages.get(1));
        com.github.lzyzsd.circleprogress.DonutProgress donut3 = findViewById(R.id.donut3);
        donut3.setProgress(percentages.get(2));
        com.github.lzyzsd.circleprogress.DonutProgress donut4 = findViewById(R.id.donut4);
        donut4.setProgress(percentages.get(3));
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
