package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;


public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //Redirection vers la page principale "MainActivity",apres 3 sec
        Runnable R = new Runnable() {
            @Override
            public void run() {
                //demarer une page
                Intent I = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(I);
                finish();
            }
        };
        // handler post delayed
        new Handler().postDelayed(R,3000);

    }
}