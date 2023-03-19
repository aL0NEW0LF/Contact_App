package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactsListAdapter extends ArrayAdapter<Contact> implements View.OnClickListener{

    private ArrayList<Contact> mContact;
    Context mContext;

    private static class ViewHolder {
        TextView listName;
        ImageButton callBtn;
    }

    public ContactsListAdapter(ArrayList<Contact> data, Context context) {
        super(context, R.layout.contacts_rv_item, data);
        this.mContact = data;
        this.mContext = context;
    }

    @Override
    public void onClick(View v) {
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Contact dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.contacts_rv_item, parent, false);
            viewHolder.listName = (TextView) convertView.findViewById(R.id.listName);
            viewHolder.callBtn = (ImageButton) convertView.findViewById(R.id.call);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.listName.setText(dataModel.getName());
        viewHolder.callBtn.setOnClickListener(this);

        // Return the completed view to render on screen
        return convertView;
    }
}
