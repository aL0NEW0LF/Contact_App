package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateNewContact extends AppCompatActivity {
    //creating a new variable for our edit text and button.
    EditText nameEdt, phoneEdt, emailEdt, jobEdt;
    Button addContactEdt, cancelBtn;
    ImageButton resetBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_contact);
        DatabaseHandler db = new DatabaseHandler(this);

        //on below line we are initializing our variables.
        nameEdt = findViewById(R.id.idName);
        phoneEdt = findViewById(R.id.idPhoneNumber);
        emailEdt = findViewById(R.id.idEmail);
        jobEdt = findViewById(R.id.idJob);
        addContactEdt = findViewById(R.id.idBtnAddContact);
        cancelBtn = findViewById(R.id.idbutcancel);
        resetBtn = findViewById(R.id.resetBtn);

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameEdt.setText("");
                phoneEdt.setText("");
                emailEdt.setText("");
                jobEdt.setText("");
            }
        });

        //on below line we are adding on click listner for our button.
        addContactEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //on below line we are getting text from our edit text.
                Contact NewContact = new Contact();
                NewContact.setName(nameEdt.getText().toString());
                NewContact.setJob(jobEdt.getText().toString());
                NewContact.setPhoneNumber(phoneEdt.getText().toString());
                NewContact.setEmail(emailEdt.getText().toString());

                //on below line we are making a text validation.
                if (TextUtils.isEmpty(NewContact._name) || TextUtils.isEmpty(NewContact._job) || TextUtils.isEmpty(NewContact._phone_number)
                        || TextUtils.isEmpty(NewContact._email)) {
                    Toast.makeText(CreateNewContact.this, "Please enter the data in all fields", Toast.LENGTH_SHORT).show();
                    } else if (!ReGexValidation.isValidPhoneNumber(NewContact._phone_number)) {
                        Toast.makeText(CreateNewContact.this, "Please, enter a valid phone number", Toast.LENGTH_SHORT).show();
                    } else if (!ReGexValidation.isValidEmail(NewContact._email)) {
                        Toast.makeText(CreateNewContact.this, "Please, enter a valid Email address", Toast.LENGTH_SHORT).show();
                    } else {
                        //calling a method to add contact.
                        db.addContact(NewContact);
                        Toast.makeText(CreateNewContact.this, "Contact added Successfully", Toast.LENGTH_SHORT).show();
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
