package com.example.doctorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private EditText username;
    private EditText password;
    Button button;
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText)findViewById(R.id.user);
        password = (EditText)findViewById(R.id.pass);
        button  = (Button)findViewById(R.id.login);
    }


    public void logit(View view) {

        String usr_name = username.getText().toString().trim();
        String pass = password.getText().toString().trim();

        if (TextUtils.isEmpty(usr_name)) {
            username.setError("Enter valid username");
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            password.setError("Enter valid password");
            return;
        }


        if(usr_name.equals("kaustubh") && pass.equals("kaustubh"))
        {
            pref = getApplicationContext().getSharedPreferences("Myshared",0);
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("islogged",true);
            editor.apply();
            Intent intent = new Intent(Login.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Username or password not valid", Toast.LENGTH_SHORT).show();
        }
    }
}
