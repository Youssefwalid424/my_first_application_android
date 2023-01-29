package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
TextView email_receiver,name_receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        name_receiver=(TextView)findViewById(R.id.name_view) ;
        email_receiver=(TextView)findViewById(R.id.email_view) ;

        Intent intent=getIntent();

        String name=intent.getStringExtra("name");
        String email=intent.getStringExtra("email");

        name_receiver.setText(name);
        email_receiver.setText(email);


    }
}