package com.example.patient;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG =MainActivity.class.getName();
    private static final int KEY=15;

    private FirebaseFirestore mFirestore;
    private CollectionReference mItems;

   // private PatientItemAdapter mAdapter;

    EditText name;
    EditText address;
    EditText phone;
    EditText closest;
    EditText dr;
    EditText perlang;
    CheckBox active;
    DatePicker birth;
    RadioGroup gender;



    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name =  findViewById(R.id.patientNameEditText);
        phone =  findViewById(R.id.patientNumberEditText);
        birth =  findViewById(R.id.birth);
        gender =  findViewById(R.id.radioGrp);
        active = findViewById(R.id.isactive);
        address =findViewById(R.id.patientAdressEditText);
        closest =findViewById(R.id.relative);
        perlang =findViewById(R.id.prefl);
        dr =findViewById(R.id.doctor);

        mFirestore=FirebaseFirestore.getInstance();
        mItems = mFirestore.collection("patients");

        mAuth=FirebaseAuth.getInstance();


    //    mAdapter=new PatientItemAdapter(this, new ArrayList<>());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void send(View view) {

        String patientName=name.getText().toString();
        String patientPhone=phone.getText().toString();
        int day  = birth.getDayOfMonth();
        int month= birth.getMonth();
        int year = birth.getYear();
        LocalDate date= LocalDate.of(year,month,day);
        boolean patientGender= gender.toString().equals("Férfi");
        String doctorbubo = dr.getText().toString();
        String close = closest.getText().toString();
        String pre = perlang.getText().toString();
        String cim = address.getText().toString();
        boolean akt=active.isChecked();

        mItems.add(new PatientItem(patientName,akt,cim,patientPhone,date.toString(),close,patientGender,doctorbubo,pre));

      //  mAdapter.notifyDataSetChanged();
        Intent intent=new Intent(this, ListActivity.class);
        intent.putExtra("KEY", 15);
        startActivity(intent);
    }

    public void cancel(View view) {
        Intent intent=new Intent(this, ListActivity.class);
        intent.putExtra("KEY", 15);
        startActivity(intent);
    }

}