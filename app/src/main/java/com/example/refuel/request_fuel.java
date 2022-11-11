package com.example.refuel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class request_fuel extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button pro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_fuel);


        Spinner spinner3 =findViewById(R.id.fuel_select);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,R.array.Fuel_needed, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);
        spinner3.setOnItemSelectedListener(this);


        Spinner spinner2 =findViewById(R.id.capacity_select);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.Capacity_count, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);



        pro=findViewById(R.id.proceed);

        //proceed clicked
        pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(request_fuel.this,conform_page.class);
                startActivity(i);
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
         String text1 = parent.getItemAtPosition(position).toString();
         if(text1 == "Select Fuels") {
           Toast.makeText(parent.getContext(), text1, Toast.LENGTH_SHORT).show();
         }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}