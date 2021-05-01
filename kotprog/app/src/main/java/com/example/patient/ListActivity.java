package com.example.patient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private ArrayList<PatientItem> mItemList;
    private PatientItemAdapter mAdapter;
    private int gridNumber = 1;

    private FirebaseFirestore mFirestore;
    private CollectionReference mItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        int key = getIntent().getIntExtra("KEY", 0);
        if (key != 15) {
            finish();
        }

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, gridNumber));
        mItemList = new ArrayList<>();
        mAdapter = new PatientItemAdapter(this, mItemList);

        mRecyclerView.setAdapter(mAdapter);

        mFirestore = FirebaseFirestore.getInstance();
        mItems = mFirestore.collection("patients");
        queryData();

    }

    private void queryData() {
        mItemList.clear();

        mItems.orderBy("name").limit(100).get().addOnSuccessListener(queryDocumentSnapshots -> {
            for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                PatientItem item = document.toObject(PatientItem.class);
                mItemList.add(item);
            }

            if (mItemList.size() == 0) {
                initalizeData();
            }

            mAdapter.notifyDataSetChanged();
        });


    }

    private void initalizeData() {
        String[] itemsList = getResources().getStringArray(R.array.names);
        TypedArray itemsActive = getResources().obtainTypedArray(R.array.active);


        for (int i = 0; i < itemsList.length; i++) {
            mItemList.add(new PatientItem(itemsList[i], itemsActive.getBoolean(i, false)));
        }
        itemsActive.recycle();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.patient_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        // final MenuItem alertMenuItem= menu.findItem(R.id)
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.new_patient) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

}