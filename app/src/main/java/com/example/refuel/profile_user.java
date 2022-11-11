package com.example.refuel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.jar.Attributes;

public class profile_user extends AppCompatActivity {
    TextView name,email,mobile,age,pincode,city;
    Button logout;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);
        name=findViewById(R.id.textView21);
        email=findViewById(R.id.textView15);
        mobile=findViewById(R.id.textView16);
        age=findViewById(R.id.textView17);
        city=findViewById(R.id.textView18);
        pincode=findViewById(R.id.textView19);

        logout=findViewById(R.id.logout);

        fAuth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();

        userid=fAuth.getCurrentUser().getUid();

        DocumentReference documentReference=fstore.collection("users").document(userid);

        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                name.setText(documentSnapshot.getString("Name"));
                email.setText(documentSnapshot.getString("Email"));
                mobile.setText(documentSnapshot.getString("Mobile"));
                age.setText(documentSnapshot.getString("Age"));
                city.setText(documentSnapshot.getString("City"));
                pincode.setText(documentSnapshot.getString("Pincode"));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),loginpage.class));
                finish();
            }
        });

    }

}