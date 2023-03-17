package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class CallNumpad extends AppCompatActivity {
    private void sendMessage(String contactNumber) {
        //in this method we are calling an intent to send sms.
        //on below line we are passing our contact number.
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + contactNumber));
        intent.putExtra("sms_body", "Enter your message");
        startActivity(intent);
    }

    private void makeCall(String contactNumber) {
        //this method is called for making a call.
        //on below line we are calling an intent to make a call.
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        //on below line we are setting data to it.
        callIntent.setData(Uri.parse("tel:" + contactNumber));
        //on below line we are checking if the calling permissions are grantedor not.
        if (ActivityCompat.checkSelfPermission(CallNumpad.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        //at last we are starting activity.
        startActivity(callIntent);
    }
}
