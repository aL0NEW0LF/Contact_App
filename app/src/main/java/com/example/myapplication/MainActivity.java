package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ImageButton but_add, searchBtn;//button add contact
    public static final int PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    ListView listView;
    DatabaseHandler db;

    ArrayList<Contact> listItem;
    ArrayAdapter adapter;

    List<Contact> contactsInfoList;

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialization
        searchBtn = findViewById(R.id.searchBtn);
        but_add = findViewById(R.id.AddContactBtn);
        listView = (ListView) findViewById(R.id.list_contact);
        listItem = new ArrayList<>();

        db = new DatabaseHandler(this);

        getAllContacts();

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                Contact dataModel = listItem.get(position);
//                Intent intent= new Intent(MainActivity.this,ContactInfo.class);
//                startActivity(intent);
//            }
//        });

        //add a listener
        but_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //move to new activity to add contact
                Intent intent= new Intent(MainActivity.this,CreateNewContact.class);
                startActivity(intent);
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //move to new activity to add contact
                Intent intent= new Intent(MainActivity.this,Search.class);
                startActivity(intent);
            }
        });


//
//        listView.setAdapter(dataAdapter);
//
//        btnGetContacts.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getAllContacts();
//            }
//        });
    }
//
////    private void getContacts(){
//        ContentResolver contentResolver = getContentResolver();
//        String contactId = null;
//        String displayName = null;
//        contactsInfoList = new ArrayList<ContactsInfo>();
//        Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");
//        if (cursor.getCount() > 0) {
//            while (cursor.moveToNext()) {
//                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));
//                if (hasPhoneNumber > 0) {
//
//                    ContactsInfo contactsInfo = new ContactsInfo();
//                    contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
//                    displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
//
//                    contactsInfo.setContactId(contactId);
//                    contactsInfo.setDisplayName(displayName);
//
//                    Cursor phoneCursor = getContentResolver().query(
//                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
//                            null,
//                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
//                            new String[]{contactId},
//                            null);
//
//                    if (phoneCursor.moveToNext()) {
//                        String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//
//                        contactsInfo.setPhoneNumber(phoneNumber);
//                    }
//
//                    phoneCursor.close();
//
//                    contactsInfoList.add(contactsInfo);
//                }
//            }
//        }
//        cursor.close();
//
//        dataAdapter = new MyCustomAdapter(MainActivity.this, R.layout.contact_info, contactsInfoList);
//        listView.setAdapter(dataAdapter);
//    }
//
//    public void requestContactPermission() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
//                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
//                        android.Manifest.permission.READ_CONTACTS)) {
//                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                    builder.setTitle("Read contacts access needed");
//                    builder.setPositiveButton(android.R.string.ok, null);
//                    builder.setMessage("Please enable access to contacts.");
//                    builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
//                        @TargetApi(Build.VERSION_CODES.M)
//                        @Override
//                        public void onDismiss(DialogInterface dialog) {
//                            requestPermissions(
//                                    new String[]
//                                            {android.Manifest.permission.READ_CONTACTS}
//                                    , PERMISSIONS_REQUEST_READ_CONTACTS);
//                        }
//                    });
//                    builder.show();
//                } else {
//                    ActivityCompat.requestPermissions(this,
//                            new String[]{android.Manifest.permission.READ_CONTACTS},
//                            PERMISSIONS_REQUEST_READ_CONTACTS);
//                }
//            } else {
//                getContacts();
//            }
//        } else {
//            getContacts();
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           String permissions[], int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode) {
//            case PERMISSIONS_REQUEST_READ_CONTACTS: {
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    getContacts();
//                } else {
//                    Toast.makeText(this, "You have disabled a contacts permission", Toast.LENGTH_LONG).show();
//                }
//                return;
//            }
//        }
//    }

    private void getAllContacts() {
        Cursor cursor1 = (Cursor) db.getAllContacts();

        if(cursor1.getCount() == 0) {
            Toast.makeText(this, "No Contacts", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor1.moveToNext()) {
                listItem.add(new Contact(Integer.parseInt(cursor1.getString(0)), cursor1.getString(1), cursor1.getString(3),
                        cursor1.getString(4), cursor1.getString(2)));;
            }

            adapter = new ContactsListAdapter(listItem, getApplicationContext());
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Contact ToInfoContact = listItem.get(position);

                    Intent intent= new Intent(getApplicationContext(), ContactInfo.class);
                    intent.putExtra("id", String.valueOf(ToInfoContact.getID()));
                    intent.putExtra("Name", ToInfoContact.getName());
                    intent.putExtra("PhoneNumber", ToInfoContact.getPhoneNumber());
                    intent.putExtra("Email", ToInfoContact.getEmail());
                    intent.putExtra("Job", ToInfoContact.getJob());
                    startActivity(intent);
                }
            });
        }
    }
}