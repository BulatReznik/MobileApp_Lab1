package com.example.lab_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lab_1.Components.Component;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonAdd = findViewById(R.id.buttonAdd);
        Button buttonChoose = findViewById(R.id.buttonChoose);
        Button buttonReset= findViewById(R.id.buttonReset);
        Button buttonDisplay = findViewById(R.id.buttonDisplay);
        Button buttonDeleteSelected = findViewById(R.id.buttonDeleteSelected);
        Button buttonChange = findViewById(R.id.buttonChange);
        Button button2 = findViewById(R.id.buttonActivity);

        ListView componentList = findViewById(R.id.componentsListView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, Component.list);
        componentList.setAdapter(adapter);

        EditText textInputLayout = findViewById(R.id.componentInput);

        buttonAdd.setOnClickListener(view -> {
            String enteredData  = textInputLayout.getText().toString();
            if (enteredData.isEmpty()) {
                Toast.makeText(this, "Введите данные" , Toast.LENGTH_SHORT).show();
            } else {
                Component.list.add(enteredData);
                componentList.setAdapter(adapter);
            }
        });

        buttonChoose.setOnClickListener(view -> {
            if( Component.list.size() > 0){
                for(int i = 0; i< Component.list.size(); i++){
                    componentList.setItemChecked(i, true);
            }}else{
                Toast.makeText(getApplicationContext(), "Лист пустой" , Toast.LENGTH_SHORT).show();
            }
        });

        buttonReset.setOnClickListener(view ->{
            for(int i = 0; i< Component.list.size(); i++) {
                componentList.setItemChecked(i, false);
            }
        });

        buttonDisplay.setOnClickListener(view ->{
            SparseBooleanArray checkedItems = componentList.getCheckedItemPositions();StringBuilder showChecked = new StringBuilder();
            String str="";
            if(checkedItems != null){
                for (int i=0; i<checkedItems.size(); i++) {
                    if (checkedItems.valueAt(i)) {
                        String item = componentList.getAdapter().getItem(checkedItems.keyAt(i)).toString();
                        str += item + ", ";
                    }
                }
                str+="was selected";
            }
            Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
        });

        buttonDeleteSelected.setOnClickListener(view -> {
            SparseBooleanArray checkedItemPositions = componentList.getCheckedItemPositions();
            int itemCount = componentList.getCount();
            for(int i = itemCount-1; i >= 0; i--){
                if(checkedItemPositions.get(i)){
                    Component.list.remove(i);
                }
            }
            componentList.setAdapter(adapter);
        });

        buttonChange.setOnClickListener(view -> {
            SparseBooleanArray checkedItemPositions = componentList.getCheckedItemPositions();
            Integer count = 0;
            Integer chosenIndex = 0;
            int itemCount = componentList.getCount();
            for(int i = itemCount - 1; i >= 0; i--){
                if(checkedItemPositions.get(i)){
                   count++;
                   chosenIndex = i;
                }
            }
            if(count>1){
                Toast.makeText(getApplicationContext(), "Choose only one element", Toast.LENGTH_SHORT).show();
            }else{
                String enteredData  = textInputLayout.getText().toString();
                Component.list.set(chosenIndex, enteredData);
                componentList.setAdapter(adapter);
            }
            componentList.setAdapter(adapter);
        });

        componentList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
                final int which_item = position;
                new AlertDialog.Builder(MainActivity.this).
                        setIcon(android.R.drawable.ic_menu_delete).
                        setTitle("Are you sure ?").
                        setMessage("Do you want delete Item?").
                        setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                Component.list.remove(which_item);
                                adapter.notifyDataSetChanged();
                            }
                })
                        .setNegativeButton("No", null)
                        .show();
                return true;
            }
        });

        button2.setOnClickListener(view -> {
            Intent changeActivity = new Intent(MainActivity.this, ComplexSearch.class);
            startActivity(changeActivity);
        });
    }
}