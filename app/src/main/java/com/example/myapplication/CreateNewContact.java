package com.example.myapplication;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateNewContact extends AppCompatActivity {
    //creating a new variable for our edit text and button.
    private EditText fnameEdt, lnameEdt, phoneEdt, emailEdt;
    private Button addContactEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_contact);
        //on below line we are initializing our variables.
        fnameEdt = findViewById(R.id.idEdtFName);
        lnameEdt = findViewById(R.id.idEdtLName);
        phoneEdt = findViewById(R.id.idEdtPhoneNumber);
        emailEdt = findViewById(R.id.idEdtEmail);
        addContactEdt = findViewById(R.id.idBtnAddContact);
        //on below line we are adding on click listner for our button.
        addContactEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //on below line we are getting text from our edit text.
                Contact NewContact = null;
                NewContact.setFName(fnameEdt.getText().toString());
                NewContact.setLName(lnameEdt.getText().toString());
                NewContact.setPhoneNumber(phoneEdt.getText().toString());
                NewContact.setEmail(emailEdt.getText().toString());

                //on below line we are making a text validation.
                if (TextUtils.isEmpty(NewContact._fname) && TextUtils.isEmpty(NewContact._lname) && TextUtils.isEmpty(NewContact._phone_number)
                        && TextUtils.isEmpty(NewContact._phone_number)) {
                    Toast.makeText(CreateNewContact.this, "Please enter the data in all fields. ", Toast.LENGTH_SHORT).show();
                } else {
                    //calling a method to add contact.
                    DatabaseHandler.addContact(NewContact);
                }
            }
        });

    }
}
