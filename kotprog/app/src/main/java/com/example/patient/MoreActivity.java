package com.example.patient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class MoreActivity extends AppCompatActivity {

    private FirebaseFirestore mFirestore;
    private CollectionReference mItems;
    private PatientItem mItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_info);


        String name = getIntent().getStringExtra("nev");
        mFirestore= FirebaseFirestore.getInstance();
        mItems = mFirestore.collection("patients");
        queryData(name);

    }

    private void queryData(String name) {

            mItems.whereEqualTo("name",name).get().addOnSuccessListener(queryDocumentSnapshots -> {
                for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                    mItem=document.toObject(PatientItem.class);
                    TextView mPatientName = findViewById(R.id.name);
                    TextView maddress = findViewById(R.id.adress);
                    TextView mphone = findViewById(R.id.phone);
                    TextView mdateOfBirth = findViewById(R.id.dateOfBurn);
                    TextView mclosestRelative = findViewById(R.id.closestTelative);
                    TextView mgender = findViewById(R.id.gender);
                    TextView mgeneralPractitioner = findViewById(R.id.generalPractitioner);
                    TextView mpreflang = findViewById(R.id.language);
                    mPatientName.setText(mItem.getName());
                    maddress.setText(mItem.getAddress());
                    mphone.setText(mItem.getPhone());
                    mdateOfBirth.setText(mItem.getDateOfBirth());
                    mclosestRelative.setText(mItem.getClosestRelative());
                    mgender.setText(mItem.getGender() ? "Férfi" : "Nő");
                    mgeneralPractitioner.setText(mItem.getGeneralPractitioner());
                    mpreflang.setText(mItem.getPreflang());
                }
        });
    }
    }