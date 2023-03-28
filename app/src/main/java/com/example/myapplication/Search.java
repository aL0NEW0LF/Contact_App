package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Search extends AppCompatActivity {
    ImageButton searchBtn;
    EditText nameSearch;
    DatabaseHandler db;
    ListView listView;
    ArrayList<Contact> listItem;
    ArrayAdapter adapter;

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //initialization
        searchBtn = findViewById(R.id.searchBtn);
        nameSearch = findViewById(R.id.nameSearch);
        listView = (ListView) findViewById(R.id.searched_contacts);
        listItem = new ArrayList<>();

        db = new DatabaseHandler(this);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ToBeSearchedName = nameSearch.getText().toString();

                getContact(ToBeSearchedName);
            }
        });
    }

    private void getContact(String name) {
        Cursor cursor1 = (Cursor) db.getContact(name);

        if (listView.getAdapter() != null && listView.getCount() > 0) {
            ((ContactsListAdapter) adapter).clear(); // clear the adapter if it already has items
            Log.i("Listview", "adapter cleared");
        }

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
