package com.example.lab_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.lab_1.Components.Component;

public class ComplexSearch extends AppCompatActivity {
    SearchView searchView;
    private ArrayAdapter<String> adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complex_search);
        searchView = findViewById(R.id.searchComponent);
        ListView listView = findViewById(R.id.secondList);
        adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, Component.list);
        listView.setAdapter(adapter2);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ComplexSearch.this.adapter2.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ComplexSearch.this.adapter2.getFilter().filter(newText);
                return false;
            }
        });
    }
}