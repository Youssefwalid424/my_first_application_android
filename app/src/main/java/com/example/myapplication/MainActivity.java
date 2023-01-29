package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
Button save,retrive,clear,play,stop,next,visit;
TextView namebox,emailbox;
EditText visit_edit_text;
SharedPreferences sharedPreferences;
public static final String mypreference="mypref";
    public static final String name_shared="namekey";
public static final String email_shared="emailkey";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // delaration button from xml
        //shared preferences
        save=(Button) findViewById(R.id.save);
        retrive=(Button) findViewById(R.id.retrive);
        clear=(Button) findViewById(R.id.clear);
        //service
        play=(Button) findViewById(R.id.play);
        stop=(Button) findViewById(R.id.stop);
        //send data
        next=(Button) findViewById(R.id.next);
        //action view for url link
        visit=(Button) findViewById(R.id.visit);
       // delaration textview from xml to send data to activiy2(extras)& saving and clearing data(shared prefernces)
        namebox=(TextView) findViewById(R.id.name);
        emailbox=(TextView) findViewById(R.id.email);
        // delaration edit text from xml
        visit_edit_text=(EditText) findViewById(R.id.visit_link);

        play.setOnClickListener(this);
        stop.setOnClickListener(this);
        next.setOnClickListener(this);
        visit.setOnClickListener(this);

        sharedPreferences=getSharedPreferences(mypreference,Context.MODE_PRIVATE);



    }
    public void Save(View view){
        String n=namebox.getText().toString();
        String e=emailbox.getText().toString();

        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(name_shared,n);
        editor.putString(email_shared,e);
        editor.commit();

    }

    public void Clear(View view){
        namebox=(TextView) findViewById(R.id.name);
        emailbox=(TextView) findViewById(R.id.email);

        namebox.setText("");
        emailbox.setText("");




    }

    public void Retrive(View view){
        namebox=(TextView) findViewById(R.id.name);
        emailbox=(TextView) findViewById(R.id.email);

        sharedPreferences=getSharedPreferences(mypreference,Context.MODE_PRIVATE);

        if (sharedPreferences.contains(name_shared)){
            namebox.setText(sharedPreferences.getString(name_shared,""));
        }
        if (sharedPreferences.contains(email_shared)){
            emailbox.setText(sharedPreferences.getString(email_shared,""));
        }

    }




    //broadcast

    public void broadcastIntent (View view) {
        Intent intent = new Intent( );
        intent.setAction("com.mybroadcast.CUSTOM_INTENT");
        sendBroadcast(intent);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play:
              startService(new Intent(this,MyService.class));
                break;
            case R.id.stop:
                stopService(new Intent(this,MyService.class));
                break;
            case R.id.next:
                String name_content=namebox.getText().toString();
                String email_content=emailbox.getText().toString();
                Intent intent= new Intent(this,MainActivity2.class);
                intent.putExtra("name",name_content);
                intent.putExtra("email",email_content);
                startActivity(intent);
                break;
            case R.id.visit:
                String url=visit_edit_text.getText().toString();
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                break;



        }


    }
}