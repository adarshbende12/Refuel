package com.example.refuel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class conform_page extends AppCompatActivity {

    Button done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conform_page);

        done=findViewById(R.id.done);
        //done clicked

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(conform_page.this,Dashboard.class);
                startActivity(i);
            }
        });
    }
}