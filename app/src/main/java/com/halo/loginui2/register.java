package com.halo.loginui2;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class register extends Activity_Login {
    DatabaseHelper helper = new DatabaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
    }

    public void onregisterClick(View v) {
        if (v.getId() == R.id.Blogin) {
            EditText name = (EditText) findViewById(R.id.tUsn);
            EditText email = (EditText) findViewById(R.id.tEmail);
            EditText pass = (EditText) findViewById(R.id.tPass);
            String namestr = name.getText().toString();
            String emailstr = email.getText().toString();
            String passstr = pass.getText().toString();


            Contact c = new Contact();
            c.setName(namestr);
            c.setEmail(emailstr);
            c.setPass(passstr);

            helper.insertContact(c);
        }
    }
}
