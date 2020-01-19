package com.example.doctorapp;



import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class splashScreen extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 2500;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splash_screen);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("Myshared",0);
        final boolean loggedIn = pref.getBoolean("islogged",false);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent mainIntent;
                if(loggedIn == true)
                {
                    mainIntent = new Intent(splashScreen.this,MainActivity.class);
                }

                else
                {
                    mainIntent = new Intent(splashScreen.this,Login.class);
                }
                splashScreen.this.startActivity(mainIntent);
                splashScreen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
