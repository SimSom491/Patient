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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {

    private boolean update;
    private FirebaseFirestore mFirestore;
    private CollectionReference mItems;
    private PatientItem mItem;

    EditText name;
    EditText address;
    EditText phone;
    EditText closest;
    EditText dr;
    EditText perlang;
    CheckBox active;
    DatePicker birth;
    RadioButton genderm;
    RadioButton genderf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        update = false;

        setContentView(R.layout.activity_main);
        name = findViewById(R.id.patientNameEditText);
        phone = findViewById(R.id.patientNumberEditText);
        birth = findViewById(R.id.birth);
        genderm = findViewById(R.id.radioM);
        genderf = findViewById(R.id.radioF);

        active = findViewById(R.id.isactive);
        address = findViewById(R.id.patientAdressEditText);
        closest = findViewById(R.id.relative);
        perlang = findViewById(R.id.prefl);
        dr = findViewById(R.id.doctor);

        mFirestore = FirebaseFirestore.getInstance();
        mItems = mFirestore.collection("patients");


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void send(View view) {
        String patientName = name.getText().toString();
        String patientPhone = phone.getText().toString();
        int day = birth.getDayOfMonth();
        int month = birth.getMonth();
        int year = birth.getYear();
        LocalDate date = LocalDate.of(year, month, day);
        boolean patientGender = genderm.isChecked();
        String doctorbubo = dr.getText().toString();
        String close = closest.getText().toString();
        String pre = perlang.getText().toString();
        String cim = address.getText().toString();
        boolean akt = active.isChecked();

        if (!update) {

            mItems.add(new PatientItem(patientName, akt, cim, patientPhone, date.toString(), close, patientGender, doctorbubo, pre));
        } else {
            mItems.whereEqualTo("name", getIntent().getStringExtra("name")).get().addOnSuccessListener(queryDocumentSnapshots -> {
                for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                    mItems.document(document.getId()).update("name", patientName, "active", akt, "address", cim, "closestRelative", close, "dateOfBirth", date.toString(), "gender", patientGender, "generalPractitioner", doctorbubo, "phone", patientPhone);

                }
            });

        }
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }

    public void cancel(View view) {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        if (getIntent().getBooleanExtra("up", false)) {
            update = true;

            mItems.whereEqualTo("name", getIntent().getStringExtra("name")).get().addOnSuccessListener(queryDocumentSnapshots -> {
                for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                    mItem = document.toObject(PatientItem.class);

                    name.setText(mItem.getName());
                    phone.setText(mItem.getPhone());
                    String[] evhonap = mItem.getDateOfBirth().split("-");
                    birth.updateDate(Integer.parseInt(evhonap[0]), Integer.parseInt(evhonap[1]) - 1, Integer.parseInt(evhonap[2]));
                    if (mItem.getGender()) {
                        genderm.toggle();
                    } else {
                        genderf.toggle();
                    }
                    active.setChecked(mItem.getActive());
                    address.setText(mItem.getAddress());
                    closest.setText(mItem.getClosestRelative());
                    perlang.setText(mItem.getPreflang());
                    dr.setText(mItem.getGeneralPractitioner());
                }
            });

        }

        super.onResume();
    }

    @Override
    protected void onPause() {
        update = false;

        super.onPause();
    }
}