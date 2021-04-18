package com.example.patient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class List extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        int key=getIntent().getIntExtra("KEY", 0);
        if(key!=15){
            finish();
        }
    }
}