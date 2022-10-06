package com.example.lab_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.SparseBooleanArray;
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
        ListView componentList = findViewById(R.id.componentsListView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, Component.list);
        componentList.setAdapter(adapter);

        EditText textInputLayout = findViewById(R.id.componentInput);

        buttonAdd.setOnClickListener(view -> {
            String enteredData  = textInputLayout.getText().toString();
            if (enteredData.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Введите данные" , Toast.LENGTH_SHORT).show();
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
            for(int i = 0; i< Component.list.size(); i++){
                componentList.setItemChecked(i, false);
            }
        });

        buttonDisplay.setOnClickListener(view ->{
            SparseBooleanArray checkedItems = componentList.getCheckedItemPositions();
            StringBuilder showChecked = new StringBuilder();
            if(checkedItems != null){
                for (int i=0; i<checkedItems.size(); i++) {
                    if (checkedItems.valueAt(i)) {
                        String item = componentList.getAdapter().getItem(checkedItems.keyAt(i)).toString();
                        showChecked.append(item).append(", ");
                    }
                }
                showChecked.append("was selected");
            }
            Toast.makeText(getApplicationContext(), showChecked.toString(), Toast.LENGTH_SHORT).show();
        });

    }
}