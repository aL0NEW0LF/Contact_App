package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateContact extends AppCompatActivity {
    EditText nameUpdate, phoneUpdate, emailUpdate, jobUpdate;
    Button updateBtn, cancelBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        DatabaseHandler db = new DatabaseHandler(this);
        Contact UpdatedContact = new Contact();

        nameUpdate = findViewById(R.id.nameUpdate);
        phoneUpdate = findViewById(R.id.phoneUpdate);
        emailUpdate = findViewById(R.id.emailUpdate);
        jobUpdate = findViewById(R.id.jobUpdate);
        updateBtn = findViewById(R.id.idBtnUpdateContact);
        cancelBtn = findViewById(R.id.idbutcancel);

        Intent intent = getIntent();
        String PassedID = intent.getStringExtra("id");
        String PassedName = intent.getStringExtra("Name");
        String PassedPhoneNumber = intent.getStringExtra("PhoneNumber");
        String PassedEmail = intent.getStringExtra("Email");
        String PassedJob = intent.getStringExtra("Job");

        nameUpdate.setText(PassedName);
        phoneUpdate.setText(PassedPhoneNumber);
        emailUpdate.setText(PassedEmail);
        jobUpdate.setText(PassedJob);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //on below line we are getting text from our edit text.
                UpdatedContact.setID(Integer.parseInt(PassedID));
                UpdatedContact.setName(nameUpdate.getText().toString());
                UpdatedContact.setJob(jobUpdate.getText().toString());
                UpdatedContact.setPhoneNumber(phoneUpdate.getText().toString());
                UpdatedContact.setEmail(emailUpdate.getText().toString());

                //on below line we are making a text validation.
                if (TextUtils.isEmpty(UpdatedContact._name) || TextUtils.isEmpty(UpdatedContact._job) || TextUtils.isEmpty(UpdatedContact._phone_number)
                        || TextUtils.isEmpty(UpdatedContact._email)) {
                    Toast.makeText(UpdateContact.this, "Please enter the data in all fields", Toast.LENGTH_SHORT).show();
                    } else if (!ReGexValidation.isValidPhoneNumber(UpdatedContact._phone_number)) {
                        Toast.makeText(UpdateContact.this, "Please, enter a valid phone number", Toast.LENGTH_SHORT).show();
                    } else if (!ReGexValidation.isValidEmail(UpdatedContact._email)) {
                        Toast.makeText(UpdateContact.this, "Please, enter a valid Email address", Toast.LENGTH_SHORT).show();
                    } else {
                        //calling a method to add contact.
                        db.updateContact(UpdatedContact);
                        Toast.makeText(UpdateContact.this, "Contact updated Successfully", Toast.LENGTH_SHORT).show();
                        Intent BackToTheMain = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(BackToTheMain);
                        finish();
                    }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent BackToTheMain = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(BackToTheMain);
                finish();
            }
        });
    }
}
