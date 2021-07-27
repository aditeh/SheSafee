package com.example.womensafety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Toast;

import java.util.ArrayList;

public class EmergencyCall extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_call);
        GPSTracker gps;
        gps = new GPSTracker(EmergencyCall.this);
        double latitude = gps.getLatitude();
        double longitude = gps.getLongitude();
        Toast.makeText(EmergencyCall.this, "Sending Location Update...", Toast.LENGTH_SHORT).show();
        SQLiteDatabase db;
        db = openOrCreateDatabase("NumDB", Context.MODE_PRIVATE, null);
        Cursor c = db.rawQuery("SELECT * FROM details", null);



        while (c.moveToNext()) {
            String target_ph_number = c.getString(1);
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(target_ph_number, null, "Please help me. I need help immediately. This is where i am now:http://maps.google.com/maps?saddr="+ latitude +longitude , null, null);

            Toast.makeText(getApplicationContext(), "Sent", Toast.LENGTH_SHORT).show();

        }
        db.close();

    }
    }
