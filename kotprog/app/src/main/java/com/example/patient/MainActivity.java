package com.example.patient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG =MainActivity.class.getName();
    private static final int KEY=15;
    EditText name;
    EditText phone;
    EditText birth;
    RadioGroup gender;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name =  findViewById(R.id.patientNameEditText);
        phone =  findViewById(R.id.patientNumberEditText);
        birth =  findViewById(R.id.patientBirthEditText);
        gender =  findViewById(R.id.radioGrp);

        mAuth=FirebaseAuth.getInstance();
    }

    public void send(View view) {

        String patientName=name.getText().toString();
        String patientPhone=phone.getText().toString();
        String patientBirth=birth.getText().toString();
        String patientGender=gender.toString();
        Log.i("MainActivity", patientName+patientPhone+patientBirth+patientGender);
    }

    public void cancel(View view) {
        Intent intent=new Intent(this,List.class);
        intent.putExtra("KEY", 15);
        startActivity(intent);
    }
}