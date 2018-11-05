package com.halo.loginui2;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Activity_Login extends AppCompatActivity {

    RelativeLayout rellay1, rellay2;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
        }
    };

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        rellay2 = (RelativeLayout) findViewById(R.id.rellay2);

        handler.postDelayed(runnable, 2000); //2000 is the timeout for the splash
    }

    public void onButtonClick(View v)
    {
        if (v.getId() == R.id.Blogin)
        {
            EditText a = (EditText) findViewById(R.id.tUsn);
            String str = a.getText().toString();
            EditText b = (EditText) findViewById(R.id.tPass);
            String pass = b.getText().toString();

            String password = helper.searchPass(str);
            if (pass.equals(password))
            {

                Intent i = new Intent(Activity_Login.this, Home.class);
                i.putExtra("Username", str);
                startActivity(i);
                }
                else {
                Toast log = Toast.makeText(Activity_Login.this, "Invalid Credentials ", Toast.LENGTH_SHORT);
                log.show();
            }
        }

            if (v.getId() == R.id.BdDisplay) {
                Intent i = new Intent(Activity_Login.this, register.class);
                startActivity(i);
            }

        }
    }
