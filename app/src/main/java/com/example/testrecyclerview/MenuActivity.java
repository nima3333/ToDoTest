package com.example.testrecyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout vue1 = findViewById(R.id.vue1);
        LinearLayout vue2 = findViewById(R.id.vue2);
        LinearLayout vue3 = findViewById(R.id.vue3);
        LinearLayout vue4 = findViewById(R.id.vue4);

        vue1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), TodoActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }
}
