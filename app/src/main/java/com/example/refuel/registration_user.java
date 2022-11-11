package com.example.refuel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class registration_user extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText name,city,pin,age,mobile,pass,memail;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    Button submit;
    FirebaseFirestore fstore;
    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_user);

        name=findViewById(R.id.name_of_user);
        city=findViewById(R.id.city_user);
        pin= findViewById(R.id.pincode_user);
        age=findViewById(R.id.age_user);
        mobile=findViewById(R.id.mobile_user);
        pass=findViewById(R.id.creating_Password);
        memail=findViewById(R.id.user_mail);


        fAuth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressBar);
        fstore=FirebaseFirestore.getInstance();
        submit=findViewById(R.id.submit_reg_user);

      /*  if(fAuth.getCurrentUser()!= null){
            Intent intent=new Intent(registration_user.this,Dashboard.class);
            startActivity(intent);
            finish();
        */



        submit.setOnClickListener(v -> {

            String email=memail.getText().toString().trim();
            String password=pass.getText().toString().trim();

            String user_name=name.getText().toString();
            String phone=mobile.getText().toString();
            String pincode=pin.getText().toString();
            String user_age=age.getText().toString();
            String user_city=city.getText().toString();


            if(TextUtils.isEmpty(email)){
                memail.setError("Email is required!");
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
                    Toast.makeText(registration_user.this,"User Created",Toast.LENGTH_SHORT).show();



                    userid= fAuth.getCurrentUser().getUid();

                    DocumentReference documentReference = fstore.collection("users").document(userid);
                    Map<String,Object> users1 = new HashMap<>();
                    users1.put("Name",user_name);
                    users1.put("Age",user_age);
                    users1.put("Mobile",phone);
                    users1.put("City",user_city);
                    users1.put("Pincode",pincode);
                    users1.put("Email",email);

                    documentReference.set(users1).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.d(TAG,"onSuccess: user profile is created for "+userid);
                        }
                    });







                    startActivity(new Intent(getApplicationContext(),loginpage.class));
                }else{
                    Toast.makeText(registration_user.this,"Error!"+ Objects.requireNonNull(task.getException()).getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}