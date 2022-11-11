package com.example.refuel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class loginpage_bunk extends AppCompatActivity {
    TextView email1,pass;
    Button reg,go,user;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage_bunk);

        //hooks
        email1=findViewById(R.id.bunk_mail);
        pass=findViewById(R.id.Password_bunk);

        reg=findViewById(R.id.to_rigister_bunk);
        go=findViewById(R.id.to_dash_bunk);
        user=findViewById(R.id.to_user);

        fAuth=FirebaseAuth.getInstance();

        progressBar=findViewById(R.id.progressBar4);

        //clicks user
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(loginpage_bunk.this,loginpage.class);
                startActivity(intent);
            }
        });
        //clicks go
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=email1.getText().toString().trim();
                String password=pass.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    email1
                            .setError("Email is required!");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    pass.setError("Password is required!");
                    return;
                }
                if(password.length()<6)
                {
                    pass.setError("Password must be >6 characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //auth the user
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Toast.makeText(loginpage_bunk.this,"User logged in",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),Dashboard_bunk.class));
                    }else{
                        Toast.makeText(loginpage_bunk.this,"Error!"+ Objects.requireNonNull(task.getException()).getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        //clicks reg

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(loginpage_bunk.this,registration_bunk.class);
                startActivity(intent);
            }
        });
    }
}