package com.example.refuel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class order_viewing extends AppCompatActivity {


    Button done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_viewing);


        done=findViewById(R.id.done_req);

        //clicking done
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(order_viewing.this,Dashboard.class);
                startActivity(intent);
            }
        });

    }
}