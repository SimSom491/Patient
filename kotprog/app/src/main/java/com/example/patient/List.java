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

import java.util.ArrayList;

public class List extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private ArrayList<PatientItem> mItemList;
    private PatientItemAdapter mAdapter;
    private int gridNumber=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        int key=getIntent().getIntExtra("KEY", 0);
        if(key!=15){
            finish();
        }

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,gridNumber));
        mItemList=new ArrayList<>();
        mAdapter=new PatientItemAdapter(this, mItemList);

        mRecyclerView.setAdapter(mAdapter);

        initalizeData();
    }

    private void initalizeData() {
        String[] itemsList=getResources().getStringArray(R.array.names);
        TypedArray itemsActive= getResources().obtainTypedArray(R.array.active);

        mItemList.clear();

        for (int i = 0; i < itemsList.length; i++) {
            mItemList.add(new PatientItem(itemsList[i], itemsActive.getBoolean(i,false)));
        }
        itemsActive.recycle();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.patient_menu,menu);
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
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == R.id.new_patient) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

}