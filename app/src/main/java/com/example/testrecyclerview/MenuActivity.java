package com.example.testrecyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout vue1 = findViewById(R.id.vue1);
        LinearLayout vue2 = findViewById(R.id.vue2);
        LinearLayout vue3 = findViewById(R.id.vue3);
        LinearLayout vue4 = findViewById(R.id.vue4);

        ArrayList<Integer> liste = FileHelper.getSize(getApplicationContext());
        TextView tv1 = findViewById(R.id.textView1);
        TextView tv2 = findViewById(R.id.textView2);
        TextView tv3 = findViewById(R.id.textView3);
        TextView tv4 = findViewById(R.id.textView4);
        tv1.setText(liste.get(0).toString());
        tv2.setText(liste.get(1).toString());
        tv3.setText(liste.get(2).toString());
        tv4.setText(liste.get(3).toString());

        vue1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), TodoActivity.class);
                myIntent.putExtra("vue", 1);
                //myIntent.putExtra("data", new Gson().toJson(FileHelper.readData(getApplicationContext())));
                startActivityForResult(myIntent, 0);
            }
        });
    }
}
