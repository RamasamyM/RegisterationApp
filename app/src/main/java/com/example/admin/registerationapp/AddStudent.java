package com.example.admin.registerationapp;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddStudent extends Main{

    StudentDatabase studentdb;
    EditText name, regid, address, mobile, email;
    Button save, reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_student_data);

        studentdb = new StudentDatabase(this);
        name = (EditText) findViewById(R.id.nameEditText);
        regid = (EditText) findViewById(R.id.regID_EditText);
        address = (EditText) findViewById(R.id.address_EditText);
        mobile = (EditText) findViewById(R.id.mobile_EditText);
        email = (EditText) findViewById(R.id.email_EditText);

        save = (Button) findViewById(R.id.save_button);
        reset = (Button) findViewById(R.id.reset_button);

    }

    public void saveDetails(View V)
    {
        String nameText = name.getText().toString();
        String regidText = regid.getText().toString();
        String addressText = address.getText().toString();
        String mobileText = mobile.getText().toString();
        String emailText = email.getText().toString();

        if(!nameText.equals("") && !regidText.equals("") && !addressText.equals("") &&
                !mobileText.equals("") && !emailText.equals(""))
        {
            ContentValues values = new ContentValues();
            values.put("studentname",nameText);
            values.put("regid", Integer.parseInt(regidText));
            values.put("address",addressText);
            values.put("mobileNo",Long.valueOf(mobileText));
            values.put("e_mail",emailText);

            studentdb.insert(values);
        }
        else
        {
            Toast.makeText(AddStudent.this, "Please fill the entire Fields", Toast.LENGTH_SHORT).show();
        }
        reset(V);
    }
    public void reset(View v)
    {
        name.setText("");
        regid.setText("");
        address.setText("");
        mobile.setText("");
        email.setText("");
    }
}

