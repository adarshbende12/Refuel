package com.example.refuel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class registration_bunk extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button reg_sub;
    EditText name,city,pin,mobile,email1,pass;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_bunk);


        Spinner spinner =findViewById(R.id.fuel_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.FuelsAvalable, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        //hooks
        name=findViewById(R.id.bunk_name);
        city=findViewById(R.id.city);
        pin=findViewById(R.id.pincode);
        mobile=findViewById(R.id.mobile);
        email1=findViewById(R.id.email_bunk);
        pass=findViewById(R.id.bunk_password);

        reg_sub=findViewById(R.id.submit_reg_bunk);

        progressBar=findViewById(R.id.progressBar);

        fAuth=FirebaseAuth.getInstance();

        //clicked submit
        reg_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email=email1.getText().toString().trim();
                String password=pass.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    email1.setError("Email is required!");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    pass.setError("Password is required!");
                    return;
                }
                if(password.length() < 6){
                    pass.setError("Password must be >6 characters!!");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //register the user in firebase
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Toast.makeText(registration_bunk.this,"User Created",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),loginpage_bunk.class));
                    }else{
                        Toast.makeText(registration_bunk.this,"Error!"+ Objects.requireNonNull(task.getException()).getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
         // String text = parent.getItemAtPosition(position).toString();
        //  if(text == "Select Fuels") {
          //    Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
         // }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}