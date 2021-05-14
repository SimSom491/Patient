package com.example.patient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.xml.namespace.QName;

public class MoreActivity extends AppCompatActivity {

    private FirebaseFirestore mFirestore;
    private CollectionReference mItems;
    private PatientItem mItem;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_info);

        name = getIntent().getStringExtra("nev");
        mFirestore = FirebaseFirestore.getInstance();
        mItems = mFirestore.collection("patients");
        queryData(name);

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.moreanim);
        findViewById(R.id.name).startAnimation(anim);
        findViewById(R.id.adress).startAnimation(anim);
        findViewById(R.id.phone).startAnimation(anim);
        findViewById(R.id.dateOfBurn).startAnimation(anim);
        findViewById(R.id.closestTelative).startAnimation(anim);
        findViewById(R.id.gender).startAnimation(anim);
        findViewById(R.id.generalPractitioner).startAnimation(anim);
        findViewById(R.id.language).startAnimation(anim);


    }

    private void queryData(String name) {

        mItems.whereEqualTo("name", name).get().addOnSuccessListener(queryDocumentSnapshots -> {
            for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                mItem = document.toObject(PatientItem.class);
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

    public void back(View view) {

        Intent intent = new Intent(this, ListActivity.class);

        startActivity(intent);
    }

    public void delete(View view) {

        //mItems.document(findViewById(R.id.name))
        mItems.whereEqualTo("name", name).get().addOnSuccessListener(queryDocumentSnapshots -> {
            for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                document.getReference().delete();

            }
        });

        Intent intent = new Intent(this, ListActivity.class);
        intent.putExtra("KEY", 15);
        startActivity(intent);
    }

    public void update(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("up", true);
        intent.putExtra("name", name);
        startActivity(intent);

    }
}