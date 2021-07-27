package com.example.womensafety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Contact extends AppCompatActivity {
    Button saveContact;
    Button senderNumber;
    ArrayList<String> Contacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        senderNumber = findViewById(R.id.SenderNum);
        saveContact = findViewById(R.id.saveContactBtn);
        saveContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    Toast.makeText(getApplicationContext(), "save started",Toast.LENGTH_LONG).show();
                    EditText name = (EditText) findViewById(R.id.first);
                    EditText number = (EditText) findViewById(R.id.phoneNoInput);
                    String str_name=name.getText().toString();
                    String str_number=number.getText().toString();
                    SQLiteDatabase db;
                    db=openOrCreateDatabase("NumDB", Context.MODE_PRIVATE, null);
                    Toast.makeText(getApplicationContext(), "db created",Toast.LENGTH_LONG).show();

                    db.execSQL("CREATE TABLE IF NOT EXISTS details(name VARCHAR,number VARCHAR);");
                    Toast.makeText(getApplicationContext(), "table created",Toast.LENGTH_LONG).show();

                    Cursor c=db.rawQuery("SELECT * FROM details", null);
                    if(c.getCount()<2)
                    {
                        db.execSQL("INSERT INTO details VALUES('"+str_name+"','"+str_number+"');");
                        Toast.makeText(getApplicationContext(), "Successfully Saved",Toast.LENGTH_SHORT).show();
                    }
                    else {

                        db.execSQL("INSERT INTO details VALUES('"+str_name+"','"+str_number+"');");
                        Toast.makeText(getApplicationContext(), "Maximun Numbers limited reached. Previous numbers are replaced.",Toast.LENGTH_SHORT).show();
                    }


                    db.close();


            }
        });
        senderNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_verify);
            }
        });
    }
}