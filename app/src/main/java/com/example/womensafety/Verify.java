package com.example.womensafety;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

public class Verify extends Activity {
    Button verify;
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText source_no = (EditText)findViewById(R.id.editText1);
                String str_source_no=source_no.getText().toString();
                SQLiteDatabase db;
                db=openOrCreateDatabase("NumDB", Context.MODE_PRIVATE, null);
                Toast.makeText(getApplicationContext(),"db created",Toast.LENGTH_SHORT).show();
                if(source_no.getText()!=null){

                    db.execSQL("CREATE TABLE IF NOT EXISTS source(number VARCHAR);");
                    db.execSQL("INSERT INTO source VALUES('"+str_source_no+"');");
                    Toast.makeText(getApplicationContext(), str_source_no+" Successfully Saved",Toast.LENGTH_SHORT).show();
                    db.close();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Enter Your Number.",Toast.LENGTH_SHORT).show();
                }

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_contact);
            }
        });


    }

    public void back(View v) {
        Intent i_back=new Intent(Verify.this,MainActivity.class);
        startActivity(i_back);

    }

}

