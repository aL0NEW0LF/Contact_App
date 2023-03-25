package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class ContactInfo extends AppCompatActivity {
    TextView DisplayName, DisplayPhoneNumber, DisplayEmail, DisplayJob;
    ImageButton homeBtn, deleteBtn, editBtn, callBtn, messageBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_info);
        DatabaseHandler db = new DatabaseHandler(this);

        //initialization
        DisplayName = findViewById(R.id.idName);
        DisplayPhoneNumber = findViewById(R.id.idPhoneNumber);
        DisplayEmail = findViewById(R.id.idEmail);
        DisplayJob = findViewById(R.id.idJob);
        homeBtn = findViewById(R.id.homeBtn);
        deleteBtn = findViewById(R.id.deleteBtn);
        editBtn = findViewById(R.id.editBtn);
        callBtn = findViewById(R.id.call);
        messageBtn = findViewById(R.id.dm);

        Intent intent = getIntent();
        String PassedID = intent.getStringExtra("id");
        String PassedName = intent.getStringExtra("Name");
        String PassedPhoneNumber = intent.getStringExtra("PhoneNumber");
        String PassedEmail = intent.getStringExtra("Email");
        String PassedJob = intent.getStringExtra("Job");

        DisplayName.setText(PassedName);
        DisplayPhoneNumber.setText(PassedPhoneNumber);
        DisplayEmail.setText(PassedEmail);
        DisplayJob.setText(PassedJob);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent BackToHomeIntent= new Intent(getApplicationContext(), MainActivity.class);
                startActivity(BackToHomeIntent);
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent UpdateIntent= new Intent(getApplicationContext(), UpdateContact.class);
                UpdateIntent.putExtra("id", PassedID);
                UpdateIntent.putExtra("Name", PassedName);
                UpdateIntent.putExtra("PhoneNumber", PassedPhoneNumber);
                UpdateIntent.putExtra("Email", PassedEmail);
                UpdateIntent.putExtra("Job", PassedJob);
                startActivity(UpdateIntent);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Contact DeletedContact = new Contact();

                Intent BackToHomeIntent= new Intent(getApplicationContext(), MainActivity.class);
                DeletedContact.setID(Integer.parseInt(PassedID));
                DeletedContact.setName(PassedName);
                DeletedContact.setPhoneNumber(PassedPhoneNumber);
                DeletedContact.setEmail(PassedEmail);
                DeletedContact.setJob(PassedJob);

                db.deleteContact(DeletedContact);

                startActivity(BackToHomeIntent);
            }
        });

        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //this method is called for making a call.
                //on below line we are calling an intent to make a call.
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + PassedPhoneNumber));
                //on below line we are setting data to it.
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(callIntent);
                } else {
                    // Permission is not granted, you need to request for it
                    ActivityCompat.requestPermissions(ContactInfo.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                }
            }
        });

        messageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //in this method we are calling an intent to send sms.
                //on below line we are passing our contact number.
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + PassedPhoneNumber));
                intent.putExtra("sms_body", "");
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                    // Permission is granted, you can send a message
                    startActivity(intent);
                } else {
                    // Permission is not granted, you need to request for it
                    ActivityCompat.requestPermissions(ContactInfo.this, new String[]{Manifest.permission.SEND_SMS}, 2);
                }
            }
        });
    }
}
