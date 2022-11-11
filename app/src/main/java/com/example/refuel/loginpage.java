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

public class loginpage extends AppCompatActivity {
    TextView email1,pass;
    Button go,reg,bunk;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        //hooks
        email1=findViewById(R.id.Username);
        pass=findViewById(R.id.Password_bunk);

        go=findViewById(R.id.to_dash_bunk);
        reg=findViewById(R.id.to_rigister_bunk);
        bunk=findViewById(R.id.textview);
        fAuth=FirebaseAuth.getInstance();

        progressBar=findViewById(R.id.progressBar2);

        //clickes go;
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
                        Toast.makeText(loginpage.this,"User logged in",Toast.LENGTH_SHORT).show();
                        //Intent intent=new Intent(registration_user.this,loginpage.class);
                        startActivity(new Intent(getApplicationContext(),Dashboard.class));
                    }else{
                        Toast.makeText(loginpage.this,"Error!"+ Objects.requireNonNull(task.getException()).getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        // clicks bunk;
        bunk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(loginpage.this,loginpage_bunk.class);
                startActivity(intent);
            }
        });

        // clicks sign up
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(loginpage.this,registration_user.class);
                startActivity(intent);
            }
        });
    }
}